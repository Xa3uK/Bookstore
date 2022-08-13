package org.fishbone.jpapractice.services;

import java.util.Optional;
import org.fishbone.jpapractice.models.Person;
import org.fishbone.jpapractice.repositories.PeopleRepository;
import org.fishbone.jpapractice.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public PersonDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUserName(username);

        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User not found ((");
        } else {
            return new PersonDetails(person.get());
        }
    }

    public Optional<Person> findUserByName(String userName){
        return peopleRepository.findByUserName(userName);
    }
}

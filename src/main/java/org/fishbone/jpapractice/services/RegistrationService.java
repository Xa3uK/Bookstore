package org.fishbone.jpapractice.services;

import org.fishbone.jpapractice.models.Person;
import org.fishbone.jpapractice.repositories.PeopleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person){
        person.setEncodedPassword(passwordEncoder.encode(person.getPass()));
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }
}

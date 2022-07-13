package org.fishbone.jpapractice.repositories;

import java.util.Optional;
import org.fishbone.jpapractice.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByUserName(String userName);
}

package org.fishbone.jpapractice.repositories;

import org.fishbone.jpapractice.models.Author;
import org.fishbone.jpapractice.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findAuthorByName(String name);
}

package org.fishbone.jpapractice.repositories;

import org.fishbone.jpapractice.models.Author;
import org.fishbone.jpapractice.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}

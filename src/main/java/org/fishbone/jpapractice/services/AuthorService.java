package org.fishbone.jpapractice.services;

import org.fishbone.jpapractice.models.Author;
import org.fishbone.jpapractice.models.Publisher;
import org.fishbone.jpapractice.repositories.AuthorRepository;
import org.fishbone.jpapractice.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthorService {
    AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findById(int id){
        return authorRepository.findById(id).orElse(null);
    }
}

package org.fishbone.jpapractice.services;

import java.util.List;
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

    public List<Author> findAll(){
        return authorRepository.findAll();
    }


    public int getIdByName(String name){
            return authorRepository.findAuthorByName(name).getId();
    }
}

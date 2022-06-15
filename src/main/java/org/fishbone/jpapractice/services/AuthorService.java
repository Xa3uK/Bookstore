package org.fishbone.jpapractice.services;

import java.util.List;
import org.fishbone.jpapractice.models.Author;
import org.fishbone.jpapractice.repositories.AuthorRepository;
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

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(int id){
        return authorRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Author author){
        authorRepository.save(author);
    }

    @Transactional
    public void deleteById(int id){
        authorRepository.deleteById(id);
    }

    @Transactional
    public int getIdByName(String name) {
        if (authorRepository.findAuthorByName(name) == null) {
            Author author = new Author();
            author.setName(name);
            authorRepository.save(author);
        }
        return authorRepository.findAuthorByName(name).getId();
    }
}

package org.fishbone.jpapractice.services;

import java.util.List;
import java.util.Optional;
import org.fishbone.jpapractice.models.Author;
import org.fishbone.jpapractice.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Transactional
    public Author findByNameOrSave(String name) {
        if (authorRepository.findAuthorByName(name) == null) {
            authorRepository.save(new Author(name));
        }
        return authorRepository.findAuthorByName(name);
    }

    @Transactional
    public void saveOrUpdate(Author author) {
        Optional<Author> optionalAuthor = authorRepository.findById(author.getId());
        if (optionalAuthor.isPresent()) {
            optionalAuthor.get().setName(author.getName());
        } else {
            authorRepository.save(author);
        }
    }

    @Transactional
    public void deleteById(int id) {
        authorRepository.deleteById(id);
    }
}

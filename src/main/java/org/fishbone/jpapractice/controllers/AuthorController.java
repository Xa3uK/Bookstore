package org.fishbone.jpapractice.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.fishbone.jpapractice.dto.AuthorDTO;
import org.fishbone.jpapractice.dto.BookDTO;
import org.fishbone.jpapractice.mappers.Mapper;
import org.fishbone.jpapractice.services.AuthorService;
import org.fishbone.jpapractice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    AuthorService authorService;
    BookService bookService;
    Mapper mapper;

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService,
                            Mapper mapper) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<AuthorDTO> getAll() {
        return authorService.findAll().stream()
            .map(author -> mapper.authorToDto(author))
            .collect(Collectors.toList());
    }

    @GetMapping("/id")
    public AuthorDTO findById(int id) {
        return mapper.authorToDto(authorService.findById(id));
    }

    @GetMapping("allBooks")
    public List<BookDTO> getAllBooksByAuthorId(int id) {
        return bookService.findBooksByAuthorId(id).stream()
            .map(book -> mapper.bookToDto(book))
            .collect(Collectors.toList());
    }

    @PostMapping
    public void addAuthor(AuthorDTO authorDTO) {
        authorService.saveOrUpdate(mapper.dtoToAuthor(authorDTO));
    }

    @DeleteMapping
    public void deleteById(int id) {
        authorService.deleteById(id);
    }
}

package org.fishbone.jpapractice.controllers;

import io.swagger.v3.oas.annotations.Operation;
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
public class AuthorsController {

    AuthorService authorService;
    BookService bookService;
    Mapper mapper;

    @Autowired
    public AuthorsController(AuthorService authorService, BookService bookService, Mapper mapper) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Get all authors", description = "Returns list of authors")
    public List<AuthorDTO> getAll() {
        return authorService.findAll().stream()
            .map(author -> mapper.authorToDto(author))
            .collect(Collectors.toList());
    }

    @GetMapping("/id")
    @Operation(summary = "Get author", description = "Returns author by Id")
    public AuthorDTO findById(int id) {
        return mapper.authorToDto(authorService.findById(id));
    }

    @GetMapping("allBooks")
    @Operation(summary = "Get all author books", description = "Returns list of books by authorId param")
    public List<BookDTO> getAllBooksByAuthorId(int id) {
        return bookService.findBooksByAuthorId(id).stream()
            .map(book -> mapper.bookToDto(book))
            .collect(Collectors.toList());
    }

    @PostMapping
    @Operation(summary = "Add new author", description = "Type name of new author or update name of existing author "
        + "by typing his Id too")
    public void addAuthor(AuthorDTO authorDTO) {
        authorService.save(mapper.dtoToAuthor(authorDTO));
    }

    @DeleteMapping
    public void deleteById(int id) {
        authorService.deleteById(id);
    }

}

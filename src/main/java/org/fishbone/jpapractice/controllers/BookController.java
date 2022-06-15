package org.fishbone.jpapractice.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.fishbone.jpapractice.dto.BookDTO;
import org.fishbone.jpapractice.mappers.Mapper;
import org.fishbone.jpapractice.services.AuthorService;
import org.fishbone.jpapractice.services.BookService;
import org.fishbone.jpapractice.services.CoverService;
import org.fishbone.jpapractice.services.LanguageService;
import org.fishbone.jpapractice.services.PublisherService;
import org.fishbone.jpapractice.services.SubThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    BookService bookService;
    PublisherService publisherService;
    SubThemeService subThemeService;
    LanguageService languageService;
    CoverService coverService;
    AuthorService authorService;
    Mapper mapper;

    @Autowired
    public BookController(BookService bookService, PublisherService publisherService, SubThemeService subThemeService,
                          LanguageService languageService, CoverService coverService, AuthorService authorService,
                          Mapper mapper) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.subThemeService = subThemeService;
        this.languageService = languageService;
        this.coverService = coverService;
        this.authorService = authorService;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<BookDTO> getAllBooks() {
        return bookService.findAll().stream()
            .map(book -> mapper.bookToDto(book))
            .collect(Collectors.toList());
    }

    @PostMapping
    public void addBook(BookDTO bookDto) {
        bookService.addBook(mapper.dtoToBook(bookDto, publisherService, subThemeService, languageService,
            coverService, authorService));
    }

    @GetMapping("/id")
    public BookDTO findById(int id) {
        return mapper.bookToDto(bookService.findById(id));
    }

    @DeleteMapping("/delete")
    public void deleteBookById(int id) {
        bookService.deleteBookById(id);
    }
}

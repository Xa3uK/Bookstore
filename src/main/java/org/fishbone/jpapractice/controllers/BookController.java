package org.fishbone.jpapractice.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.fishbone.jpapractice.dto.BookDTO;
import org.fishbone.jpapractice.models.Author;
import org.fishbone.jpapractice.models.Book;
import org.fishbone.jpapractice.models.Cover;
import org.fishbone.jpapractice.models.Language;
import org.fishbone.jpapractice.models.Publisher;
import org.fishbone.jpapractice.models.SubTheme;
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
    AuthorService authorService;
    CoverService coverService;
    LanguageService languageService;
    SubThemeService subThemeService;

    @Autowired
    public BookController(BookService bookService, PublisherService publisherService, AuthorService authorService,
                          CoverService coverService, LanguageService languageService, SubThemeService subThemeService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
        this.coverService = coverService;
        this.languageService = languageService;
        this.subThemeService = subThemeService;
    }

    @GetMapping("/all")
    public List<BookDTO> getAllBooks() {
        return bookService.findAll().stream()
            .map(book -> getBookDto(book.getId()))
            .collect(Collectors.toList());
    }

    @PostMapping
    public void addBook(Book book) {
        bookService.addBook(book);
    }

    @GetMapping("/id")
    public BookDTO findById(int id) {
        return getBookDto(id);
    }

    @DeleteMapping("/delete")
    public void deleteBookById(int id){
        bookService.deleteBookById(id);
    }

    public BookDTO getBookDto(int id){
        Book book = bookService.findById(id);
        Publisher publisher = publisherService.findById(book.getPublisherId());
        Author author = authorService.findById(book.getAuthorId());
        Cover cover = coverService.findById(book.getCoverId());
        Language language = languageService.findById(book.getLanguageId());
        SubTheme subTheme = subThemeService.findById(book.getSubThemeId());

        return new BookDTO(
            book.getTitle(),
            book.getId(),
            publisher.getName(),
            language.getName(),
            subTheme.getName(),
            cover.getName(),
            author.getName()
        );
    }
}

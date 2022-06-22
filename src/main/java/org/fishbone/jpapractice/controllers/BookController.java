package org.fishbone.jpapractice.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.fishbone.jpapractice.dto.BookDTO;
import org.fishbone.jpapractice.mappers.Mapper;
import org.fishbone.jpapractice.repositories.BookCriteriaRepository.BookPage;
import org.fishbone.jpapractice.repositories.BookCriteriaRepository.BookSearchCriteria;
import org.fishbone.jpapractice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    BookService bookService;
    Mapper mapper;

    @Autowired
    public BookController(BookService bookService, Mapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<BookDTO> getAllBooks() {
        return bookService.findAll().stream()
            .map(book -> mapper.bookToDto(book))
            .collect(Collectors.toList());
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<BookDTO>> getBooksWithFilter(
        @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
        @RequestParam(required = false, defaultValue = "5") Integer pageSize,
        @RequestParam(required = false, defaultValue = "title") String sortBy,
        @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String publisherTheme,
        @RequestParam(required = false) String subThemeName,
        @RequestParam(required = false) String authorName) {

        return new ResponseEntity<>(bookService.getAllWithFilter(
            new BookPage(pageNumber, pageSize, sortDirection, sortBy),
            new BookSearchCriteria(title, publisherTheme, subThemeName, authorName)),
            HttpStatus.OK
        );
    }

    @PostMapping
    public void addBook(
        @RequestParam String title,
        @RequestParam Integer price,
        @RequestParam String publisherName,
        @RequestParam String languageName,
        @RequestParam String authorName,
        @RequestParam String subTheme,
        @RequestParam String coverType) {

        bookService.addBook(mapper.dtoToBook(
            new BookDTO(title,price,publisherName,languageName,subTheme,coverType,authorName)));
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

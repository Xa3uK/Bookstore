package org.fishbone.jpapractice.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.fishbone.jpapractice.dto.BookDTO;
import org.fishbone.jpapractice.mappers.Mapper;
import org.fishbone.jpapractice.models.Book;
import org.fishbone.jpapractice.repositories.BookCriteriaRepository;
import org.fishbone.jpapractice.repositories.BookCriteriaRepository.BookPage;
import org.fishbone.jpapractice.repositories.BookCriteriaRepository.BookSearchCriteria;
import org.fishbone.jpapractice.repositories.BookRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CoverService coverService;
    private final LanguageService languageService;
    private final PublisherService publisherService;
    private final SubThemeService subThemeService;
    private final BookCriteriaRepository bookCriteriaRepository;

    private final Mapper mapper;

    public BookService(BookRepository bookRepository, AuthorService authorService, CoverService coverService,
                       LanguageService languageService, PublisherService publisherService,
                       SubThemeService subThemeService,
                       BookCriteriaRepository bookCriteriaRepository, Mapper mapper) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.coverService = coverService;
        this.languageService = languageService;
        this.publisherService = publisherService;
        this.subThemeService = subThemeService;
        this.bookCriteriaRepository = bookCriteriaRepository;
        this.mapper = mapper;
    }

    public Book findById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    public Page<BookDTO> getAllWithFilter(BookPage bookPage, BookSearchCriteria bookSearchCriteria) {
        return bookCriteriaRepository.findAllWithFilters(bookPage, bookSearchCriteria);
    }

    @Transactional
    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void addBook(Book book) {
        book.setCover(coverService.findByNameOrSave(book.getCover().getName()));
        book.setAuthor(authorService.findByNameOrSave(book.getAuthor().getName()));
        book.setLanguage(languageService.findByNameOrSave(book.getLanguage().getName()));
        book.setPublisher(publisherService.findByNameOrSave(book.getPublisher().getName()));
        book.setSubTheme(subThemeService.findByNameOrSave(book.getSubTheme().getName()));
        bookRepository.save(book);
    }
}

package org.fishbone.jpapractice.services;

import java.util.List;
import java.util.Optional;
import org.fishbone.jpapractice.models.Book;
import org.fishbone.jpapractice.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BookService {

    BookRepository bookRepository;
    AuthorService authorService;
    CoverService coverService;
    LanguageService languageService;
    PublisherService publisherService;
    SubThemeService subThemeService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService, CoverService coverService,
                       LanguageService languageService, PublisherService publisherService,
                       SubThemeService subThemeService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.coverService = coverService;
        this.languageService = languageService;
        this.publisherService = publisherService;
        this.subThemeService = subThemeService;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }


    public Book findById(int id){
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    public List<Book> findBooksByAuthorId(int id){
        return bookRepository.findBooksByAuthorId(id);
    }

    @Transactional
    public void deleteBookById(int id){
        bookRepository.deleteById(id);
    }

    @Transactional
    public void addBook(Book book){
        book.setCover(coverService.findByName(book.getCover().getName()));
        book.setAuthor(authorService.findByName(book.getAuthor().getName()));
        book.setLanguage(languageService.findByName(book.getLanguage().getName()));
        book.setPublisher(publisherService.findByName(book.getPublisher().getName()));
        book.setSubTheme(subThemeService.findByName(book.getSubTheme().getName()));
        bookRepository.save(book);
    }
}

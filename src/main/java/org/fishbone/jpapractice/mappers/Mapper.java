package org.fishbone.jpapractice.mappers;

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
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    BookService bookService;
    PublisherService publisherService;
    AuthorService authorService;
    CoverService coverService;
    LanguageService languageService;
    SubThemeService subThemeService;

    @Autowired
    public Mapper(BookService bookService, PublisherService publisherService, AuthorService authorService,
                  CoverService coverService, LanguageService languageService, SubThemeService subThemeService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
        this.coverService = coverService;
        this.languageService = languageService;
        this.subThemeService = subThemeService;
    }

    public BookDTO bookToDto(Book book) {
        Publisher publisher = book.getPublisher();
        Author author = book.getAuthor();
        Cover cover = book.getCover();
        Language language = book.getLanguage();
        SubTheme subTheme = book.getSubTheme();

        return new BookDTO(
            book.getTitle(),
            book.getId(),
            book.getPrice(),
            publisher.getName(),
            language.getName(),
            subTheme.getName(),
            cover.getName(),
            author.getName()
        );
    }

    public Book dtoToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setPublisherId(publisherService.getIdByName(bookDTO.getPublisherName()));
        book.setAuthorId(authorService.getIdByName(bookDTO.getAuthorName()));
        book.setLanguageId(languageService.getIdByName(bookDTO.getLanguageType()));
        book.setCoverId(coverService.getIdByName(bookDTO.getCoverType()));
        book.setSubThemeId(subThemeService.getIdByName(bookDTO.getSubThemeName()));
        book.setPrice(bookDTO.getPrice());
        book.setTitle(bookDTO.getBookTitle());
        return book;
    }
}

package org.fishbone.jpapractice.mappers;

import org.fishbone.jpapractice.dto.AuthorDTO;
import org.fishbone.jpapractice.dto.BookDTO;
import org.fishbone.jpapractice.models.Author;
import org.fishbone.jpapractice.models.Book;
import org.fishbone.jpapractice.services.AuthorService;
import org.fishbone.jpapractice.services.CoverService;
import org.fishbone.jpapractice.services.LanguageService;
import org.fishbone.jpapractice.services.PublisherService;
import org.fishbone.jpapractice.services.SubThemeService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    @Mappings({
        @Mapping(target = "publisherId", ignore = true),
        @Mapping(target = "subThemeId", ignore = true),
        @Mapping(target = "languageId", ignore = true),
        @Mapping(target = "authorId", ignore = true),
        @Mapping(target = "coverId", ignore = true)
    })
    Book dtoToBook(BookDTO bookDTO, @Context PublisherService publisherService, SubThemeService subThemeService,
                   LanguageService languageService, CoverService coverService, AuthorService authorService);

    @AfterMapping
    default void map(@MappingTarget Book book, BookDTO bookDTO, @Context PublisherService publisherService,
                     SubThemeService subThemeService, LanguageService languageService, AuthorService authorService,
                     CoverService coverService) {
        book.setPublisherId(publisherService.getIdByName(bookDTO.getPublisherName()));
        book.setSubThemeId(subThemeService.getIdByName(bookDTO.getSubThemeName()));
        book.setLanguageId(languageService.getIdByName(bookDTO.getLanguageType()));
        book.setAuthorId(authorService.getIdByName(bookDTO.getAuthorName()));
        book.setCoverId(coverService.getIdByName(bookDTO.getCoverType()));
    }

    @Mappings({
        @Mapping(target = "publisherName", source = "book.publisher.name"),
        @Mapping(target = "languageType", source = "book.language.name"),
        @Mapping(target = "subThemeName", source = "book.subTheme.name"),
        @Mapping(target = "coverType", source = "book.cover.name"),
        @Mapping(target = "authorName", source = "book.author.name")
    })
    BookDTO bookToDto(Book book);


    Author dtoToAuthor(AuthorDTO authorDTO);

    AuthorDTO authorToDto(Author author);
}


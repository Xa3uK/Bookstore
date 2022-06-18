package org.fishbone.jpapractice.mappers;

import org.fishbone.jpapractice.dto.AuthorDTO;
import org.fishbone.jpapractice.dto.BookDTO;
import org.fishbone.jpapractice.models.Author;
import org.fishbone.jpapractice.models.Book;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    @Mappings({
        @Mapping(target = "publisher.name", source = "publisherName"),
        @Mapping(target = "language.name", source = "languageName"),
        @Mapping(target = "subTheme.name", source = "subThemeName"),
        @Mapping(target = "cover.name", source = "coverType"),
        @Mapping(target = "author.name", source = "authorName")
    })
    Book dtoToBook(BookDTO bookDTO);

    @InheritInverseConfiguration
    BookDTO bookToDto(Book book);

    AuthorDTO authorToDto(Author author);
    Author dtoToAuthor(AuthorDTO authorDTO);
}


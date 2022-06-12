package org.fishbone.jpapractice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDTO {
    String bookTitle;
    int id;
    String publisherName;
    String language;
    String subTheme;
    String cover;
    String author;
}

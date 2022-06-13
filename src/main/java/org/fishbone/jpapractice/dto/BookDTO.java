package org.fishbone.jpapractice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDTO {
    String bookTitle;
    int id;
    int price;
    String publisherName;
    String languageType;
    String subThemeName;
    String coverType;
    String authorName;
}

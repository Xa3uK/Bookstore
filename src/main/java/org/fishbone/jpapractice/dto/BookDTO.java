package org.fishbone.jpapractice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    String title;
    int id;
    int price;
    String publisherName;
    String languageType;
    String subThemeName;
    String coverType;
    String authorName;
}

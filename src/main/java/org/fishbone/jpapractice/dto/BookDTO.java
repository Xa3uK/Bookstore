package org.fishbone.jpapractice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDTO {

    private String title;
    private int id;
    private int price;
    private String publisherName;
    private String languageName;
    private String subThemeName;
    private String coverType;
    private String authorName;

    public BookDTO(String title, int price, String publisherName, String languageName, String subThemeName,
                   String coverType, String authorName) {
        this.title = title;
        this.price = price;
        this.publisherName = publisherName;
        this.languageName = languageName;
        this.subThemeName = subThemeName;
        this.coverType = coverType;
        this.authorName = authorName;
    }
}

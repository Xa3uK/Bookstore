package org.fishbone.jpapractice.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDTO {

    private int id;
    @Size(min = 5, max = 50, message = "Title length should be 5-50")
    @NotEmpty(message = "Title should be not empty")
    private String title;

    @Pattern(regexp = "^[1-9]{1,10}$", message = "Price should contains only positive digits without spaces")
    @Size(min = 1, max = 6, message = "Price should be between 1-999999")
    private String price;

    @Size(min = 5, max = 50, message = "Publisher name length should be 5-50")
    @NotEmpty(message = "Publisher should be not empty")
    private String publisherName;

    @Size(min = 5, max = 70, message = "Theme length should be 5-70")
    @NotEmpty(message = "Theme should be not empty")
    private String subThemeName;
    @Size(min = 5, max = 100, message = "Author name length should be 5-100")
    @NotEmpty(message = "Author should be not empty")
    private String authorName;

    private String coverType;
    private String languageName;

    public BookDTO(String title, String price, String publisherName, String languageName, String subThemeName,
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

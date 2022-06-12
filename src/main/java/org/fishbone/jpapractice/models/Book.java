package org.fishbone.jpapractice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "title")
    String title;

    @Column(name = "image_url")
    String url;

    @Column(name = "vendor_code")
    int vendorCode;

    @Column(name = "pages")
    int pages;

    @Column(name = "price")
    int price;

    @Column(name = "publisher_id")
    int publisherId;

    @Column(name = "sub_theme_id")
    int subThemeId;

    @Column(name = "language_id")
    int languageId;

    @Column(name = "cover_id")
    int coverId;

    @Column(name = "author_id")
    int authorId;

    @Column(name = "year")
    int year;
}

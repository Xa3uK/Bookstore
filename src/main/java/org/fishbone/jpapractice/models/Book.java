package org.fishbone.jpapractice.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "title")
    String title;

    @Column(name = "price")
    int price;

    @Column(name = "publisher_id")
    int publisherId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", insertable = false, updatable = false)
    Publisher publisher;

    @Column(name = "sub_theme_id")
    int subThemeId;

    @ManyToOne()
    @JoinColumn(name = "sub_theme_id", insertable = false, updatable = false)
    SubTheme subTheme;

    @Column(name = "language_id")
    int languageId;

    @ManyToOne()
    @JoinColumn(name = "language_id", insertable = false, updatable = false)
    Language language;

    @Column(name = "cover_id")
    int coverId;

    @ManyToOne()
    @JoinColumn(name = "cover_id", insertable = false, updatable = false)
    Cover cover;

    @Column(name = "author_id")
    int authorId;

    @ManyToOne()
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    Author author;
}

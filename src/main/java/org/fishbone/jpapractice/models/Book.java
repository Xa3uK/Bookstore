package org.fishbone.jpapractice.models;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    Publisher publisher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_theme_id")
    SubTheme subTheme;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id")
    Language language;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cover_id")
    Cover cover;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    Author author;
}

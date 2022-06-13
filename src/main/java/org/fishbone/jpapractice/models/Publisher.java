package org.fishbone.jpapractice.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "publisher")
    String name;

    @OneToMany(mappedBy = "publisher")
    List<Book> books;
}

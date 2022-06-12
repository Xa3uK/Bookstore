package org.fishbone.jpapractice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "covers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cover {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "cover_type")
    String name;
}

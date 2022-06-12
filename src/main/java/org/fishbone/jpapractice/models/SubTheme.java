package org.fishbone.jpapractice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sub_themes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTheme {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "sub_theme")
    String name;
}

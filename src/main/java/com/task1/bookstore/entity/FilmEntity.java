package com.task1.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "film")
public class FilmEntity extends ProductEntity {

    @Column(name = "director_name" )
    private String directorName ;
    @Column(name = "imdb_rate")
    private double imdbRate ;
}

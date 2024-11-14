package com.task1.bookstore.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FilmDTO extends ProductDTO {
    private String directorName ;
    private double imdbRate ;
}


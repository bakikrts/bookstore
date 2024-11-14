package com.task1.bookstore.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductDTO {
    private Long productId;
    private String name ;
    private String Genre;
    private LocalDate releaseDate;
    private double basePrice;

}

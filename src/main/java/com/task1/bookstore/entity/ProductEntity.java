package com.task1.bookstore.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
public class ProductEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name")
    private String name;
    @Column(name = "genre")
    private String genre;
    @Column(name = "release_Date")
    private LocalDate releaseDate;
    @Column(name = "base_Price")
    private double basePrice;
}

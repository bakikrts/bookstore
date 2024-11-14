package com.task1.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "book")
public class BookEntity extends ProductEntity {


    @Column(name = "isbn_number")
    private String isbn;

    @Column(name = "writer_name")
    private String writerName;


}
package com.task1.bookstore.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductOrderRequestDTO {

    private List<ProductOrderDTO> products;
}

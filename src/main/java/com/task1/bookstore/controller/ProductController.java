package com.task1.bookstore.controller;

import com.task1.bookstore.dto.ProductDTO;
import com.task1.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("list-products")
public class ProductController {

    @Autowired
    private  ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOs = productService.getAllProduct();
        return ResponseEntity.ok(productDTOs);
    }


}


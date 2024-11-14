package com.task1.bookstore.repository;


import com.task1.bookstore.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<ProductEntity, Integer> {

    @Query("SELECT p FROM ProductEntity p")
    List<ProductEntity> findAllProducts();
    }



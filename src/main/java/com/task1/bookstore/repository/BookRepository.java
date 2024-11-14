package com.task1.bookstore.repository;

import com.task1.bookstore.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository  extends JpaRepository<BookEntity,Long> {
}

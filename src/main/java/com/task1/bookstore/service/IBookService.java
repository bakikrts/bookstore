package com.task1.bookstore.service;

import com.task1.bookstore.dto.BookDTO;
import com.task1.bookstore.entity.BookEntity;

import java.util.List;

public interface IBookService  {
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(Long id, BookDTO bookDTO);
    void deleteBook(Long id);
}

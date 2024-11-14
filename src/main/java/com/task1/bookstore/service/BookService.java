package com.task1.bookstore.service;

import com.task1.bookstore.dto.BookDTO;
import com.task1.bookstore.entity.BookEntity;
import com.task1.bookstore.mapper.BookMapper;
import com.task1.bookstore.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }
    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().
                stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id).map(bookMapper::toDTO)
                .orElse(null);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        BookEntity bookEntity = bookMapper.toEntity(bookDTO);
        bookEntity = bookRepository.save(bookEntity);
        return bookMapper.toDTO(bookEntity);
    }
    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        BookEntity existingBook = bookRepository.findById(id).orElse(null);

        if (existingBook != null) {
            BeanUtils.copyProperties(bookDTO, existingBook, "id");
            BookEntity updatedBook = bookRepository.save(existingBook);
            return bookMapper.toDTO(updatedBook);
        } return null;
    }
    @Override
    public void deleteBook(Long id){
       if (!bookRepository.existsById(id)) {
          return;
       }bookRepository.deleteById(id);


    }
}

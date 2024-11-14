package com.task1.bookstore.controller;

import com.task1.bookstore.dto.BookDTO;
import com.task1.bookstore.service.IBookService;
import com.task1.bookstore.service.IPricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
   private  IBookService bookService;
    @Autowired
    private IPricingService pricingService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> bookDTOs = bookService.getAllBooks();
        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/final-price")
    public ResponseEntity<List<BookDTO>> getAllBooksWithFinalPrice() {
        List<BookDTO> bookDTOs = bookService.getAllBooks();
        bookDTOs.forEach(bookDTO ->  bookDTO.setBasePrice(pricingService.getBookPriceForPricingRule(bookDTO.getBasePrice())));
        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        if(id != null) {
        BookDTO bookDTO= bookService.getBookById(id);
        return ResponseEntity.ok(bookDTO);
        }return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
      BookDTO newBook = bookService.createBook(bookDTO);
        return ResponseEntity.ok(newBook);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        BookDTO newBookDTO = bookService.updateBook(id,bookDTO);
        return ResponseEntity.ok(newBookDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id) {
        if(id != null ) {
            bookService.deleteBook(id);
            return ResponseEntity.ok().build();
        }return ResponseEntity.notFound().build();

    }
}


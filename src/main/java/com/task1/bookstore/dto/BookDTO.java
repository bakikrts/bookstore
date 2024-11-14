package com.task1.bookstore.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookDTO  extends ProductDTO {

    private String isbn;
    private String writerName;


}

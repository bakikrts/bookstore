package com.task1.bookstore.mapper;


import com.task1.bookstore.dto.BookDTO;
import com.task1.bookstore.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDTO(BookEntity bookEntity);

    BookEntity toEntity(BookDTO bookDTO);
}

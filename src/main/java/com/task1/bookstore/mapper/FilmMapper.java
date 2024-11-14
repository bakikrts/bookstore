package com.task1.bookstore.mapper;

import com.task1.bookstore.dto.FilmDTO;
import com.task1.bookstore.entity.FilmEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilmMapper {

    FilmDTO toDTO(FilmEntity filmEntity);

    FilmEntity toEntity(FilmDTO filmDTO);


}
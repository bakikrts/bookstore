package com.task1.bookstore.mapper;

import com.task1.bookstore.dto.MusicDTO;
import com.task1.bookstore.entity.MusicEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicMapper  {

    MusicDTO toDTO(MusicEntity musicEntity);

    MusicEntity toEntity(MusicDTO musicDTO);

}

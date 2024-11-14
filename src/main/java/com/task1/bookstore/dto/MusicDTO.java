package com.task1.bookstore.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MusicDTO extends ProductDTO {
    private String singerName;
    private Long numberOfSongs;

}

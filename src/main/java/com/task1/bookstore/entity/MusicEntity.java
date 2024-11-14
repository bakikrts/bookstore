package com.task1.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "music_album")
public class MusicEntity extends ProductEntity {

    @Column(name = "singer_name")
    private String singerName;
    @Column(name = "number_of_songs")
    private Long numberOfSongs;
}

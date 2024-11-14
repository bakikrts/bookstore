package com.task1.bookstore.service;

import com.task1.bookstore.dto.MusicDTO;

import java.util.List;

public interface IMusicService {
    List<MusicDTO> getAllMusics();
    MusicDTO getMusicById(Long id);
    void deleteMusic(Long id);
    MusicDTO updateMusic(Long id ,MusicDTO musicDTO);
    MusicDTO createMusic(MusicDTO musicDTO);
}

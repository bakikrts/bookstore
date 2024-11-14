package com.task1.bookstore.service;

import com.task1.bookstore.dto.FilmDTO;

import java.util.List;

public interface IFilmService {
    List<FilmDTO> getAllFilms();
    FilmDTO getFilmById(Long id);
    FilmDTO createFilm(FilmDTO filmDTO);
    FilmDTO updateFilm(Long id ,FilmDTO filmDTO);
    void deleteFilm(Long id);
}

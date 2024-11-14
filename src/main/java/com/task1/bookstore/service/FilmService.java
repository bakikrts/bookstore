package com.task1.bookstore.service;

import com.task1.bookstore.dto.FilmDTO;
import com.task1.bookstore.entity.FilmEntity;
import com.task1.bookstore.mapper.FilmMapper;
import com.task1.bookstore.repository.FilmRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService implements IFilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;


    public FilmService(FilmRepository filmRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;

    }

    @Override
    public List<FilmDTO> getAllFilms() {
        return filmRepository.findAll()
                .stream().map(filmMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FilmDTO getFilmById(Long id) {
        return filmRepository.findById(id)
                .map(filmMapper::toDTO).orElse(null);
    }

    @Override
    public FilmDTO createFilm(FilmDTO filmDTO) {
        FilmEntity filmEntity = filmMapper.toEntity(filmDTO);
        filmEntity = filmRepository.save(filmEntity);
        return filmMapper.toDTO(filmEntity);
    }

    @Override
    public FilmDTO updateFilm(Long id, FilmDTO filmDTO) {
        FilmEntity existingentity = filmRepository.findById(id).orElse(null);
        if (existingentity != null) {
            BeanUtils.copyProperties(filmDTO, existingentity, "id");
            filmRepository.save(existingentity);
            return filmMapper.toDTO(existingentity);
        }
        return null;
    }

    @Override
    public void deleteFilm(Long id) {
        if (!filmRepository.existsById(id)) {
            return;
        }
        filmRepository.deleteById(id);
    }
}

package com.task1.bookstore.controller;

import com.task1.bookstore.dto.FilmDTO;
import com.task1.bookstore.service.IFilmService;
import com.task1.bookstore.service.IPricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private  IFilmService filmService;
    @Autowired
    private IPricingService pricingService;
    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllFilm(){
        List<FilmDTO>  filmDTOs = filmService.getAllFilms();
        return ResponseEntity.ok(filmDTOs);
    }
    @GetMapping("/final-price")
    public ResponseEntity<List<FilmDTO>>getAllFilmsWithFinalPrice(){
        List<FilmDTO>  filmDTOs = filmService.getAllFilms();
        filmDTOs.forEach(filmDTO -> filmDTO.setBasePrice(pricingService.getFilmPriceForPricingRule(filmDTO.getBasePrice())));
        return ResponseEntity.ok(filmDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable Long id) {
        if (id != null) {
            FilmDTO filmDTO = filmService.getFilmById(id);
            return ResponseEntity.ok(filmDTO);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<FilmDTO> createFilm(@RequestBody FilmDTO filmDTO) {
        FilmDTO newFilm = filmService.createFilm(filmDTO);
        return ResponseEntity.ok(newFilm);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> updateFilm(@PathVariable Long id, @RequestBody FilmDTO filmDTO) {
        filmService.updateFilm(id, filmDTO);
        return ResponseEntity.ok(filmDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FilmDTO> deleteFilm(@PathVariable Long id) {
        if (id != null) {
            filmService.deleteFilm(id);
            return ResponseEntity.ok().build();
        }return ResponseEntity.notFound().build();


    }
}
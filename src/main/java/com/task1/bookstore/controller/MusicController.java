package com.task1.bookstore.controller;

import com.task1.bookstore.dto.MusicDTO;
import com.task1.bookstore.service.IMusicService;
import com.task1.bookstore.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/musics")
public class MusicController {

   @Autowired
   private IMusicService musicService;
    @Autowired
    private PricingService pricingService;

    @GetMapping
    public ResponseEntity<List<MusicDTO>> getMusic() {
        List<MusicDTO> musicDTOs  = musicService.getAllMusics();
        return ResponseEntity.ok(musicDTOs);
    }
    @GetMapping("/final-price")
    public ResponseEntity<List<MusicDTO>> getMusicWithFinalPrice(){
        List<MusicDTO> musicDTOs  = musicService.getAllMusics();
        musicDTOs.forEach(musicDTO -> musicDTO.setBasePrice(pricingService.getMusicPriceForPricingRule(musicDTO.getBasePrice())));
        return ResponseEntity.ok(musicDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MusicDTO>  getMusicById(@PathVariable Long id){
        if(id != null) {
            MusicDTO musicDTO = musicService.getMusicById(id);
            return ResponseEntity.ok(musicDTO);
        }return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<MusicDTO> createMusic(@RequestBody MusicDTO musicDTO){
      MusicDTO newMusic = musicService.createMusic(musicDTO);
        return ResponseEntity.ok(newMusic);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MusicDTO> updateMusic(@PathVariable Long id, @RequestBody MusicDTO musicDTO){
        musicService.updateMusic(id, musicDTO);
        return ResponseEntity.ok(musicDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MusicDTO> deleteMusic(@PathVariable Long id) {
        if (id != null) {
            musicService.deleteMusic(id);
            return ResponseEntity.ok().build();
        }return ResponseEntity.notFound().build();
    }
}


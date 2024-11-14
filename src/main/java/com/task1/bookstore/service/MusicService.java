package com.task1.bookstore.service;

import com.task1.bookstore.dto.MusicDTO;
import com.task1.bookstore.entity.MusicEntity;
import com.task1.bookstore.mapper.MusicMapper;
import com.task1.bookstore.repository.MusicRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MusicService implements IMusicService {

   private final MusicMapper musicMapper;
   private final MusicRepository musicRepository;

    public MusicService(MusicMapper musicMapper, MusicRepository musicRepository) {
        this.musicMapper = musicMapper;
        this.musicRepository = musicRepository;
    }

    @Override
    public List<MusicDTO> getAllMusics() {
        return   musicRepository.findAll().
                    stream().map(musicMapper::toDTO)
                            .collect(Collectors.toList());
    }
    @Override
    public MusicDTO getMusicById(Long id) {
        return  musicRepository.findById(id).
                map(musicMapper::toDTO).
                orElse(null);
    }
    @Override
    public void deleteMusic(Long id) {
        if(!musicRepository.existsById(id)) {
           return ;
        }musicRepository.deleteById(id);
    }
    @Override
    public MusicDTO updateMusic(Long id ,MusicDTO musicDTO) {
     MusicEntity existingMusic = musicRepository.findById(id).orElse(null);
         if(existingMusic != null) {
             BeanUtils.copyProperties(musicDTO, existingMusic,"id");
             MusicEntity updateMusic = musicRepository.save(existingMusic);
             return musicMapper.toDTO(updateMusic);
         }return null;
    }
    @Override
    public MusicDTO createMusic(MusicDTO musicDTO) {
        MusicEntity musicEntity = musicMapper.toEntity(musicDTO);
        musicEntity = musicRepository.save(musicEntity);
        return musicMapper.toDTO(musicEntity);
    }
}

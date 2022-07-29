package com.che.smartkitchen.service.serviceImpl;

import com.che.smartkitchen.dto.MusicCreateRequest;
import com.che.smartkitchen.dto.MusicDto;
import com.che.smartkitchen.dto.MusicUpdateRequest;
import com.che.smartkitchen.entity.Music;
import com.che.smartkitchen.enums.MusicStatus;
import com.che.smartkitchen.exception.BizException;
import com.che.smartkitchen.exception.ExceptionType;
import com.che.smartkitchen.mapper.MusicMapper;
import com.che.smartkitchen.repository.MusicRepository;
import com.che.smartkitchen.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MusicServiceImpl implements MusicService {

    MusicMapper musicMapper;
    MusicRepository musicRepository;

    @Override
    public MusicDto create(MusicCreateRequest musicCreateRequest) {
        Music music = musicMapper.createEntity(musicCreateRequest);
        music.setStatus(MusicStatus.DRAFT);
        return musicMapper.toDto(musicRepository.save(music));

    }

    @Override
    public MusicDto update(String id, MusicUpdateRequest musicUpdateRequest) {
        Music existMusic = getMusic(id);
        Music music = musicMapper.updateEntity(existMusic, musicUpdateRequest);
        return musicMapper.toDto(musicRepository.save(music));
    }

    private Music getMusic(String id) {
        Optional<Music> musicOptional = musicRepository.findById(id);
        if (!musicOptional.isPresent()) {
            throw new BizException(ExceptionType.MUSIC_NOT_FOUND);
        }
        return musicOptional.get();
    }

    @Override
    public List<MusicDto> list() {
        return musicRepository.findAll().stream().map(music -> musicMapper.toDto(music)).collect(Collectors.toList());
    }

    @Override
    public void publish(String id) {
        Music existMusic = getMusic(id);
        existMusic.setStatus(MusicStatus.PUBLISHED);
        musicRepository.save(existMusic);
    }

    @Override
    public void close(String id) {
        Music music = getMusic(id);
        music.setStatus(MusicStatus.CLOSED);
        musicRepository.save(music);
    }

    @Autowired
    public void setMusicMapper(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }

    @Autowired
    public void setMusicRepository(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }
}

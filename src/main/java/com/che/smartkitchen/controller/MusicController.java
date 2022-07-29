package com.che.smartkitchen.controller;

import com.che.smartkitchen.dto.MusicCreateRequest;
import com.che.smartkitchen.dto.MusicUpdateRequest;
import com.che.smartkitchen.mapper.MusicMapper;
import com.che.smartkitchen.service.MusicService;
import com.che.smartkitchen.vo.MusicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/musics")
public class MusicController {
    MusicService musicService;
    MusicMapper musicMapper;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<MusicVo> list() {
        return musicService.list().stream().map(musicDto -> musicMapper.toVo(musicDto)).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MusicVo create(@Validated @RequestBody MusicCreateRequest musicCreateRequest) {
        return musicMapper.toVo(musicService.create(musicCreateRequest));
    }

    @PostMapping("/{id}/publish")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void publish(@PathVariable String id) {
        musicService.publish(id);
    }

    @PostMapping("/{id}/close")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void close(@PathVariable String id) {
        musicService.close(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MusicVo update(@Validated @PathVariable String id, @RequestBody MusicUpdateRequest musicUpdateRequest) {
        return musicMapper.toVo(musicService.update(id, musicUpdateRequest));
    }

    @Autowired
    public void setMusicService(MusicService musicService) {
        this.musicService = musicService;
    }

    @Autowired
    public void setMusicMapper(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }
}

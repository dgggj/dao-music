package com.che.smartkitchen.service;

import com.che.smartkitchen.dto.MusicCreateRequest;
import com.che.smartkitchen.dto.MusicDto;
import com.che.smartkitchen.dto.MusicUpdateRequest;

import java.util.List;

public interface MusicService {
    MusicDto create(MusicCreateRequest musicCreateRequest);

    MusicDto update(String id, MusicUpdateRequest musicUpdateRequest);

    List<MusicDto> list();

    void publish(String id);

    void close(String id);
}

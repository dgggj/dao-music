package com.che.smartkitchen.mapper;

import com.che.smartkitchen.dto.MusicCreateRequest;
import com.che.smartkitchen.dto.MusicDto;
import com.che.smartkitchen.dto.MusicUpdateRequest;
import com.che.smartkitchen.entity.Music;
import com.che.smartkitchen.vo.MusicVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "Spring")
@Component
public interface MusicMapper {


    MusicDto toDto(Music music);

    MusicVo toVo(MusicDto musicDto);


    Music updateEntity(@MappingTarget Music music, MusicUpdateRequest musicUpdateRequest);

    Music createEntity(MusicCreateRequest musicCreateRequest);
}

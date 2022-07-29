package com.che.smartkitchen.mapper;

import com.che.smartkitchen.dto.SiteSettingDto;
import com.che.smartkitchen.vo.SiteSettingVo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SettingMapper {
    SiteSettingVo toVo(SiteSettingDto siteSettingDto);
}

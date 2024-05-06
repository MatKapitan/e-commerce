package com.matkap.ecommerce.dto;

import com.matkap.ecommerce.dto.requestDto.user.SiteUserRequestDto;
import com.matkap.ecommerce.model.user.SiteUser;
import org.springframework.stereotype.Component;

@Component
public class SiteUserMapper implements IMapper<SiteUser, SiteUserRequestDto>{
    @Override
    public SiteUserRequestDto toDto(SiteUser entity) {
        SiteUserRequestDto siteUserResponseDto = new SiteUserRequestDto();
        siteUserResponseDto.setUsername(entity.getUsername());
        siteUserResponseDto.setPassword(entity.getPassword());
        siteUserResponseDto.setEmailAddress(entity.getEmailAddress());
        siteUserResponseDto.setPhoneNumber(entity.getPhoneNumber());
        return siteUserResponseDto;
    }


    @Override
    public SiteUser toEntity(SiteUserRequestDto dto) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(dto.getUsername());
        siteUser.setPassword(dto.getPassword());
        siteUser.setEmailAddress(dto.getEmailAddress());
        siteUser.setPhoneNumber(dto.getPhoneNumber());
        return siteUser;
    }
}

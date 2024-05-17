package com.matkap.ecommerce.service.user.impl;

import com.matkap.ecommerce.dto.SiteUserMapper;
import com.matkap.ecommerce.dto.requestDto.user.SiteUserRequestDto;
import com.matkap.ecommerce.dto.responseDto.user.SiteUserResponseDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.user.SiteUser;
import com.matkap.ecommerce.repository.user.SiteUserRepository;
import com.matkap.ecommerce.repository.user.projections.SiteUserAddresses;
import com.matkap.ecommerce.service.user.SiteUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteUserServiceImpl implements SiteUserService {

    private final SiteUserRepository siteUserRepository;

    private final SiteUserMapper siteUserMapper;

    public SiteUserServiceImpl(SiteUserRepository siteUserRepository, SiteUserMapper siteUserMapper) {
        this.siteUserRepository = siteUserRepository;
        this.siteUserMapper = siteUserMapper;
    }


    @Override
    public SiteUserRequestDto createSiteUser(SiteUserRequestDto siteUserRequestDto) {

        SiteUser siteUser = siteUserMapper.toEntity(siteUserRequestDto);
        return siteUserMapper.toDto(siteUserRepository.save(siteUser));

    }

    @Override
    public List<SiteUserResponseDto> getSiteUsers() {
        return siteUserRepository.findAllSiteUsers();
    }

    @Override
    public SiteUserResponseDto getSiteUserById(Long siteUserId) {
        return siteUserRepository.findSideUsersById(siteUserId)
                .orElseThrow(()-> new EntityNotFoundException(siteUserId , SiteUser.class));
    }
// TODO delete one
    @Override
    public SiteUser getSiteUser(Long siteUserId) {
        return siteUserRepository.findById(siteUserId)
                .orElseThrow(()-> new EntityNotFoundException(siteUserId , SiteUser.class));
    }

    @Override
    public void deleteSiteUser(Long siteUserId) {
        SiteUser siteUser = getSiteUser(siteUserId);
        siteUserRepository.delete(siteUser);
    }

    @Override
    public SiteUserRequestDto editSiteUser(Long siteUserId, SiteUserRequestDto siteUserRequestDto) {
        SiteUser siteUser = getSiteUser(siteUserId);
        siteUser.setUsername(siteUserRequestDto.getUsername());
        siteUser.setPassword(siteUserRequestDto.getPassword());
        siteUser.setEmailAddress(siteUserRequestDto.getEmailAddress());
        siteUser.setPhoneNumber(siteUserRequestDto.getPhoneNumber());

        return siteUserMapper.toDto(siteUserRepository.save(siteUser));
    }

    @Override
    public SiteUserAddresses getAllAddressesByUser(Long siteUserId) {
        return siteUserRepository.findByAddresses_SiteUser_Id(siteUserId);
    }
}

package com.matkap.ecommerce.service.user.impl;

import com.matkap.ecommerce.dto.requestDto.user.SiteUserRequestDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.user.SiteUser;
import com.matkap.ecommerce.repository.user.SiteUserRepository;
import com.matkap.ecommerce.service.user.SiteUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteUserServiceImpl implements SiteUserService {

    private final SiteUserRepository siteUserRepository;

    public SiteUserServiceImpl(SiteUserRepository siteUserRepository) {
        this.siteUserRepository = siteUserRepository;
    }


    @Override
    public SiteUser createSiteUser(SiteUserRequestDto siteUserRequestDto) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(siteUserRequestDto.getUsername());
        siteUser.setPassword(siteUserRequestDto.getPassword());
        siteUser.setEmail_address(siteUserRequestDto.getEmail_address());
        siteUser.setPhone_number(siteUserRequestDto.getPhone_number());

        return siteUserRepository.save(siteUser);
    }

    @Override
    public List<SiteUser> getSiteUsers() {
        return siteUserRepository.findAll();
    }

    @Override
    public SiteUser getSiteUserById(Long siteUserId) {
        return getSiteUser(siteUserId);
    }

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
    public SiteUser editSiteUser(Long siteUserId, SiteUserRequestDto siteUserRequestDto) {
        SiteUser siteUser = getSiteUser(siteUserId);
        siteUser.setUsername(siteUserRequestDto.getUsername());
        siteUser.setPassword(siteUserRequestDto.getPassword());
        siteUser.setEmail_address(siteUserRequestDto.getEmail_address());
        siteUser.setPhone_number(siteUserRequestDto.getPhone_number());

        return siteUserRepository.save(siteUser);
    }
}

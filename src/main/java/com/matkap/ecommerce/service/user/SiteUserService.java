package com.matkap.ecommerce.service.user;

import com.matkap.ecommerce.dto.requestDto.user.SiteUserRequestDto;
import com.matkap.ecommerce.dto.responseDto.user.SiteUserResponseDto;
import com.matkap.ecommerce.model.user.SiteUser;
import com.matkap.ecommerce.repository.user.projections.SiteUserAddresses;

import java.util.List;

public interface SiteUserService {


    public SiteUserRequestDto createSiteUser(SiteUserRequestDto siteUserRequestDto);
    public List<SiteUserResponseDto> getSiteUsers();
    public SiteUserResponseDto getSiteUserById(Long siteUserId);
    public SiteUser getSiteUser(Long siteUserId);
    public void deleteSiteUser(Long siteUserId);
    public SiteUserRequestDto editSiteUser(Long siteUserId, SiteUserRequestDto siteUserRequestDto);

    public SiteUserAddresses getAllAddressesByUser(Long siteUserId);

}

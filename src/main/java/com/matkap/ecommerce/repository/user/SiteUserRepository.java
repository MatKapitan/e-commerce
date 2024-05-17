package com.matkap.ecommerce.repository.user;

import com.matkap.ecommerce.model.user.SiteUser;
import com.matkap.ecommerce.repository.user.projections.SiteUserAddresses;
import org.springframework.data.jpa.repository.JpaRepository;
import com.matkap.ecommerce.dto.responseDto.user.SiteUserResponseDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
    SiteUserAddresses findByAddresses_SiteUser_Id(Long id);


    @Query("SELECT new com.matkap.ecommerce.dto.responseDto.user.SiteUserResponseDto(a.id,a.emailAddress,a.phoneNumber,a.password,a.username) from SiteUser a")
    List<SiteUserResponseDto> findAllSiteUsers();

    @Query("SELECT new com.matkap.ecommerce.dto.responseDto.user.SiteUserResponseDto(a.id,a.emailAddress,a.phoneNumber,a.password,a.username) from SiteUser a WHERE a.id = :id")
    Optional<SiteUserResponseDto> findSideUsersById(@Param("id") Long id);
}

package com.matkap.ecommerce.repository.user;

import com.matkap.ecommerce.model.user.SiteUser;
import com.matkap.ecommerce.repository.user.projections.SiteUserAddresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
    SiteUserAddresses findByAddresses_SiteUser_Id(Long id);








}

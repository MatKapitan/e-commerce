package com.matkap.ecommerce.repository.user;

import com.matkap.ecommerce.model.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
}

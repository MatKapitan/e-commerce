package com.matkap.ecommerce.repository.user;

import com.matkap.ecommerce.model.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Transactional
    @Modifying
    @Query("update Address a set a.defaultAddress = false where a.siteUser.id = ?1")
    void updateDefaultAddressBySiteUser(Long siteUserId);




}

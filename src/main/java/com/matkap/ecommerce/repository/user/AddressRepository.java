package com.matkap.ecommerce.repository.user;

import com.matkap.ecommerce.dto.projection.AddressAndUserIdProjectionR;
import com.matkap.ecommerce.model.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {



    @Query("select a from Address a where a.siteUser.id = ?1 and a.defaultAddress = ?2")
    List<Address> findCurrentDefaultAddressesByUser(Long SiteUserId, Boolean defaultAddress);

    //class base projection
    @Query("select new com.matkap.ecommerce.dto.projection.AddressAndUserIdProjectionR(a.id, a.siteUser.id) from Address a where a.id = ?1")
    AddressAndUserIdProjectionR findAddressAndUserId(Long addressId);

    @Query("select a from Address a where a.id = ?1")
    Optional<AddressDto> findAddressInfoForOrder(Long addressId);




}

package com.matkap.ecommerce.repository.user;

import com.matkap.ecommerce.model.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

package com.matkap.ecommerce.repository.user.projections;

import com.matkap.ecommerce.model.user.Address;

import java.util.List;

/**
 * A Projection for the {@link com.matkap.ecommerce.model.user.SiteUser} entity
 */
public interface SiteUserAddresses {
    List<Address> getAddresses();
}
package com.matkap.ecommerce.service.user;

import com.matkap.ecommerce.dto.projection.AddressAndUserIdProjectionR;
import com.matkap.ecommerce.dto.requestDto.user.AddressRequestDto;
import com.matkap.ecommerce.model.user.Address;
import com.matkap.ecommerce.model.user.Country;
import com.matkap.ecommerce.repository.user.AddressDto;

import java.util.List;

public interface AddressService {



    public Address createAddress(AddressRequestDto addressRequestDto);
    public List<Address> getAddresses();
    public List<Country> getCountries();
    public Address getAddressById(Long addressId);
    public Address getAddress(Long addressId);
    public Country getCountry(Long countryId);
    public void deleteAddress(Long addressId);
    public Address editAddress(Long addressId, AddressRequestDto addressRequestDto);
    public void setDefaultAddress(Long addressId);

    public AddressAndUserIdProjectionR getAddressAndSiteUserId(Long id);
    public AddressDto getAddressInfoForOrder(Long addressId);

}

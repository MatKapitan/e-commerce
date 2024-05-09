package com.matkap.ecommerce.service.user;

import com.matkap.ecommerce.dto.requestDto.user.AddressRequestDto;
import com.matkap.ecommerce.model.user.Address;
import com.matkap.ecommerce.model.user.Country;

import java.util.List;

public interface AddressService {



    public AddressRequestDto createAddress(AddressRequestDto addressRequestDto);
    public List<AddressRequestDto> getAddresses();
    public List<Country> getCountries();
    public AddressRequestDto getAddressById(Long addressId);
    public Address getAddress(Long addressId);
    public Country getCountry(Long countryId);
    public void deleteAddress(Long addressId);
    public AddressRequestDto editAddress(Long addressId, AddressRequestDto addressRequestDto);
    public void setDefaultAddress(Long addressId);
}

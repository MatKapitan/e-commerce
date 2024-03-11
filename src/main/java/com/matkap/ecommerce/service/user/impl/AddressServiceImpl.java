package com.matkap.ecommerce.service.user.impl;

import com.matkap.ecommerce.dto.requestDto.user.AddressRequestDto;
import com.matkap.ecommerce.exception.EntityNotFoundException;
import com.matkap.ecommerce.model.user.Address;
import com.matkap.ecommerce.model.user.Country;
import com.matkap.ecommerce.repository.user.AddressRepository;
import com.matkap.ecommerce.repository.user.CountryRepository;
import com.matkap.ecommerce.service.user.AddressService;
import com.matkap.ecommerce.service.user.SiteUserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final CountryRepository countryRepository;

    private final SiteUserService siteUserService;


    public AddressServiceImpl(AddressRepository addressRepository, CountryRepository countryRepository, SiteUserService siteUserService) {
        this.addressRepository = addressRepository;
        this.countryRepository = countryRepository;
        this.siteUserService = siteUserService;
    }


    @Override
    @Transactional
    public Address createAddress(AddressRequestDto addressRequestDto) {
        Address address = new Address();
        address.setSiteUser(siteUserService.getSiteUser(addressRequestDto.getSiteUserId()));
        Country country = getCountry(addressRequestDto.getCountryId());
        address.setCountry(country);
        address.setUnitNumber(addressRequestDto.getUnitNumber());
        address.setStreetNumber(addressRequestDto.getStreetNumber());
        address.setAddressLine1(addressRequestDto.getAddressLine1());
        address.setAddressLine2(addressRequestDto.getAddressLine2());
        address.setCity(addressRequestDto.getCity());
        address.setRegion(addressRequestDto.getRegion());
        address.setPostalCode(addressRequestDto.getPostalCode());
        //default address
        if(addressRequestDto.getDefaultAddress() == Boolean.TRUE){
            addressRepository.updateDefaultAddressBySiteUser(addressRequestDto.getSiteUserId());
            address.setDefaultAddress(Boolean.TRUE);
        }else{
            address.setDefaultAddress(Boolean.FALSE);
        }
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Address getAddressById(Long addressId) {
        return getAddress(addressId);
    }

    @Override
    public Address getAddress(Long addressId) {
        return addressRepository.findById(addressId).orElseThrow(
                () -> new EntityNotFoundException(addressId, Address.class));
    }

    @Override
    public Country getCountry(Long countryId) {
        return countryRepository.findById(countryId).orElseThrow(
                () -> new EntityNotFoundException(countryId, Country.class));
    }

    @Override
    public void deleteAddress(Long addressId) {
        Address address = getAddress(addressId);
        addressRepository.delete(address);
    }

    @Override

    public Address editAddress(Long addressId, AddressRequestDto addressRequestDto) {
        Address address = getAddress(addressId);
        address.setSiteUser(siteUserService.getSiteUser(addressRequestDto.getSiteUserId()));
        Country country = getCountry(addressRequestDto.getCountryId());
        address.setCountry(country);
        address.setUnitNumber(addressRequestDto.getUnitNumber());
        address.setStreetNumber(addressRequestDto.getStreetNumber());
        address.setAddressLine1(addressRequestDto.getAddressLine1());
        address.setAddressLine2(addressRequestDto.getAddressLine2());
        address.setCity(addressRequestDto.getCity());
        address.setRegion(addressRequestDto.getRegion());
        address.setPostalCode(addressRequestDto.getPostalCode());
        addressRepository.save(address);
        return address;
    }

    @Override
    public void setDefaultAddress(Long addressId) {
        //get address to set default
        Address address = getAddress(addressId);
        //get user id
        Long siteUserId = address.getSiteUser().getId();
        addressRepository.updateDefaultAddressBySiteUser(siteUserId);
        // set new address as default
        address.setDefaultAddress(Boolean.TRUE);
        addressRepository.save(address);
    }
}

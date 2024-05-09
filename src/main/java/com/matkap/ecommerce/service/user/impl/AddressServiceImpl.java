package com.matkap.ecommerce.service.user.impl;

import com.matkap.ecommerce.dto.AddressMapper;
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

    private final AddressMapper addressMapper;


    public AddressServiceImpl(AddressRepository addressRepository, CountryRepository countryRepository, SiteUserService siteUserService, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.countryRepository = countryRepository;
        this.siteUserService = siteUserService;
        this.addressMapper = addressMapper;
    }


    @Override
    @Transactional
    public AddressRequestDto createAddress(AddressRequestDto addressRequestDto) {
        Address address = addressMapper.toEntity(addressRequestDto);
        //
        address.setSiteUser(siteUserService.getSiteUser(addressRequestDto.getSiteUserId()));
        Country country = getCountry(addressRequestDto.getCountryId());
        address.setCountry(country);
        //default address
        if(addressRequestDto.getDefaultAddress() == Boolean.TRUE){
            addressRepository.updateDefaultAddressBySiteUser(addressRequestDto.getSiteUserId());
            address.setDefaultAddress(Boolean.TRUE);
        }else{
            address.setDefaultAddress(Boolean.FALSE);
        }
        return addressMapper.toDto(addressRepository.save(address));
    }

    @Override
    public List<AddressRequestDto> getAddresses() {
        return addressMapper.toDtoList(addressRepository.findAll());
    }

    @Override
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    @Override
    public AddressRequestDto getAddressById(Long addressId) {
        return addressMapper.toDto(getAddress(addressId));
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

    public AddressRequestDto editAddress(Long addressId, AddressRequestDto addressRequestDto) {
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
        return addressMapper.toDto(address);
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

package com.matkap.ecommerce.dto;

import com.matkap.ecommerce.dto.requestDto.user.AddressRequestDto;
import com.matkap.ecommerce.model.user.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements IMapper<Address, AddressRequestDto>{
    @Override
    public AddressRequestDto toDto(Address entity) {
        AddressRequestDto dto = new AddressRequestDto();
        dto.setUnitNumber(entity.getUnitNumber());
        dto.setStreetNumber(entity.getStreetNumber());
        dto.setAddressLine1(entity.getAddressLine1());
        dto.setAddressLine2(entity.getAddressLine2());
        dto.setCity(entity.getCity());
        dto.setRegion(entity.getRegion());
        dto.setPostalCode(entity.getPostalCode());
        dto.setSiteUserId(entity.getSiteUser().getId());
        dto.setCountryId(entity.getCountry().getId());
        dto.setDefaultAddress(entity.getDefaultAddress());
        return dto;
    }

    @Override
    public Address toEntity(AddressRequestDto dto) {
        Address address = new Address();
        address.setUnitNumber(dto.getUnitNumber());
        address.setStreetNumber(dto.getStreetNumber());
        address.setAddressLine1(dto.getAddressLine1());
        address.setAddressLine2(dto.getAddressLine2());
        address.setCity(dto.getCity());
        address.setRegion(dto.getRegion());
        address.setPostalCode(dto.getPostalCode());
        return address;
    }
}

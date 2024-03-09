package com.matkap.ecommerce.controller.user;


import com.matkap.ecommerce.dto.projection.AddressAndUserIdProjectionR;
import com.matkap.ecommerce.dto.requestDto.user.AddressRequestDto;
import com.matkap.ecommerce.model.user.Address;
import com.matkap.ecommerce.model.user.Country;
import com.matkap.ecommerce.service.user.AddressService;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {


    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @PostMapping("/create")
    public ResponseEntity<Address> createAddress(@Valid @RequestBody AddressRequestDto addressRequestDto){
        Address address = addressService.createAddress(addressRequestDto);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping("/get/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long addressId){
        Address address = addressService.getAddressById(addressId);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Address>> getAllAddresses(){
        List<Address> addresses = addressService.getAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
    @GetMapping("/country/all")
    public ResponseEntity<List<Country>> getAllCountries(){
        List<Country> countries = addressService.getCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId){
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{addressId}")
    public ResponseEntity<Address> editAddress(@PathVariable Long addressId,
                                               @Valid @RequestBody AddressRequestDto addressRequestDto){
        Address address = addressService.editAddress(addressId, addressRequestDto);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PutMapping("/set-default/{addressId}")
    public ResponseEntity<Void> setDefaultAddress(@PathVariable Long addressId){
        addressService.setDefaultAddress(addressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<AddressAndUserIdProjectionR> testLol(@PathVariable Long id){
        AddressAndUserIdProjectionR addressAndSiteUserId = addressService.getAddressAndSiteUserId(id);
        return new ResponseEntity<>(addressAndSiteUserId, HttpStatus.OK);
    }
}

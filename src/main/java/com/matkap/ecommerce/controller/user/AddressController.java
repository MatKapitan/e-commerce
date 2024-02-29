package com.matkap.ecommerce.controller.user;


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

    @GetMapping("/get/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id){
        Address address = addressService.getAddressById(id);
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Address> editAddress(@PathVariable Long id,
                                               @Valid @RequestBody AddressRequestDto addressRequestDto){
        Address address = addressService.editAddress(id, addressRequestDto);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }
}

package com.matkap.ecommerce.controller.user;


import com.matkap.ecommerce.dto.requestDto.user.AddressRequestDto;
import com.matkap.ecommerce.exception.ErrorResponse;
import com.matkap.ecommerce.model.user.Address;
import com.matkap.ecommerce.model.user.Country;
import com.matkap.ecommerce.service.user.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Address  Controller", description = "Create, retrieve, delete and edit addresses" )
public class AddressController {


    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of address"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Create Address", description = "Creates a address from the provided payload")
    @PostMapping("/create")
    public ResponseEntity<AddressRequestDto> createAddress(@Valid @RequestBody AddressRequestDto addressRequestDto){
        AddressRequestDto address = addressService.createAddress(addressRequestDto);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Address doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of address", content = @Content(schema = @Schema(implementation = Address.class))),
    })
    @GetMapping("/get/{addressId}")
    public ResponseEntity<AddressRequestDto> getAddressById(@PathVariable Long addressId){
        AddressRequestDto address = addressService.getAddressById(addressId);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }
    @ApiResponse(responseCode = "200", description = "Successful retrieval of addresses", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Address.class))))
    @Operation(summary = "Retrieve all Addresses", description = "Provides a list of all addresses" )
    @GetMapping("/all")
    public ResponseEntity<List<AddressRequestDto>> getAllAddresses(){
        List<AddressRequestDto> addresses = addressService.getAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Successful retrieval of countries", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Country.class))))
    @Operation(summary = "Retrieve all Countries", description = "Provides a list of all countries" )
    @GetMapping("/country/all")
    public ResponseEntity<List<Country>> getAllCountries(){
        List<Country> countries = addressService.getCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Address doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful deletion of Address")
    })
    @Operation(summary = "Deletes Address", description = "Deletes a address based on id" )
    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId){
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Contact doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful edit of site user", content = @Content(schema = @Schema(implementation = Address.class))),
    })
    @Operation(summary = "Edit Address", description = "Edits a address from the provided payload and id")
    @PutMapping("/edit/{addressId}")
    public ResponseEntity<AddressRequestDto> editAddress(@PathVariable Long addressId,
                                               @Valid @RequestBody AddressRequestDto addressRequestDto){
        AddressRequestDto address = addressService.editAddress(addressId, addressRequestDto);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PutMapping("/set-default/{addressId}")
    public ResponseEntity<Void> setDefaultAddress(@PathVariable Long addressId){
        addressService.setDefaultAddress(addressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

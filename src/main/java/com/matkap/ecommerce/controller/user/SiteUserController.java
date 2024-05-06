package com.matkap.ecommerce.controller.user;


import com.matkap.ecommerce.dto.requestDto.user.SiteUserRequestDto;
import com.matkap.ecommerce.exception.ErrorResponse;
import com.matkap.ecommerce.model.user.SiteUser;
import com.matkap.ecommerce.repository.user.projections.SiteUserAddresses;
import com.matkap.ecommerce.service.user.SiteUserService;
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
@RequestMapping(value = "/site-user", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Site user Controller", description = "Create, retrieve, delete and edit site users" )
public class SiteUserController {

    private final SiteUserService siteUserService;

    public SiteUserController(SiteUserService siteUserService) {
        this.siteUserService = siteUserService;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of site user"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Create Site user", description = "Creates a site user from the provided payload")
    @PostMapping("/create")
    public ResponseEntity<SiteUserRequestDto> createSiteUser(@Valid @RequestBody SiteUserRequestDto siteUserRequestDto){
        SiteUserRequestDto siteUser = siteUserService.createSiteUser(siteUserRequestDto);
        return new ResponseEntity<>(siteUser, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Site user doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of contact", content = @Content(schema = @Schema(implementation = SiteUser.class))),
    })
    @Operation(summary = "Get Site user by id", description = "Returns a site user based on an ID")
    @GetMapping("/get/{siteUserId}")
    public ResponseEntity<SiteUserRequestDto> getSiteUserById(@PathVariable Long siteUserId){
        SiteUserRequestDto siteUser = siteUserService.getSiteUserById(siteUserId);
        return new ResponseEntity<>(siteUser, HttpStatus.OK);
    }
    @ApiResponse(responseCode = "200", description = "Successful retrieval of all addresses from site user", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SiteUserAddresses.class))))
    @Operation(summary = "Retrieve all addresses from site user", description = "Provides a list of all addresses from site user" )
    @GetMapping("/get/{siteUserId}/addresses")
    public ResponseEntity<SiteUserAddresses> getAllAddressesBySiteUser(@PathVariable Long siteUserId){
        SiteUserAddresses allAddressesByUser = siteUserService.getAllAddressesByUser(siteUserId);
        return new ResponseEntity<>(allAddressesByUser, HttpStatus.OK);
    }
    //TODO check up this handler method


    @ApiResponse(responseCode = "200", description = "Successful retrieval of contacts", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SiteUser.class))))
    @Operation(summary = "Retrieve all Site users", description = "Provides a list of all site users" )
    @GetMapping("/all")
    public ResponseEntity<List<SiteUserRequestDto>> getAllUsers(){
        List<SiteUserRequestDto> siteUsers = siteUserService.getSiteUsers();
        return new ResponseEntity<>(siteUsers, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Site user doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful deletion of site user")
    })
    @Operation(summary = "Deletes order line", description = "Deletes a site user based on id" )
    @DeleteMapping("/delete/{siteUserId}")
    public ResponseEntity<Void> deleteSiteUser(@PathVariable Long siteUserId){
        siteUserService.deleteSiteUser(siteUserId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Contact doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful edit of site user", content = @Content(schema = @Schema(implementation = SiteUser.class))),
    })
    @Operation(summary = "Edit Site user", description = "Edits a site user from the provided payload and id")
    @PutMapping("/edit/{siteUserId}")
    public ResponseEntity<SiteUserRequestDto> editSiteUser(@PathVariable Long siteUserId,
                                                 @Valid @RequestBody SiteUserRequestDto siteUserRequestDto){
        SiteUserRequestDto siteUser = siteUserService.editSiteUser(siteUserId, siteUserRequestDto);
        return new ResponseEntity<>(siteUser, HttpStatus.OK);
    }
}

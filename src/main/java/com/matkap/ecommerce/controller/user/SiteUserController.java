package com.matkap.ecommerce.controller.user;


import com.matkap.ecommerce.dto.requestDto.user.SiteUserRequestDto;
import com.matkap.ecommerce.model.user.SiteUser;
import com.matkap.ecommerce.service.user.SiteUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/site-user")
public class SiteUserController {

    private final SiteUserService siteUserService;

    public SiteUserController(SiteUserService siteUserService) {
        this.siteUserService = siteUserService;
    }

    @PostMapping("/create")
    public ResponseEntity<SiteUser> createSiteUser(@Valid @RequestBody SiteUserRequestDto siteUserRequestDto){
        SiteUser siteUser = siteUserService.createSiteUser(siteUserRequestDto);
        return new ResponseEntity<>(siteUser, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SiteUser> getSiteUserById(@PathVariable Long id){
        SiteUser siteUser = siteUserService.getSiteUserById(id);
        return new ResponseEntity<>(siteUser, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SiteUser>> getAllUsers(){
        List<SiteUser> siteUsers = siteUserService.getSiteUsers();
        return new ResponseEntity<>(siteUsers, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSiteUser(@PathVariable Long id){
        siteUserService.deleteSiteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<SiteUser> editSiteUser(@PathVariable Long id,
                                                 @Valid @RequestBody SiteUserRequestDto siteUserRequestDto){
        SiteUser siteUser = siteUserService.editSiteUser(id, siteUserRequestDto);
        return new ResponseEntity<>(siteUser, HttpStatus.OK);
    }
}

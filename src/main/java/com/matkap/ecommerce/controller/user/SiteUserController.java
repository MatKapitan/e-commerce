package com.matkap.ecommerce.controller.user;


import com.matkap.ecommerce.dto.requestDto.user.SiteUserRequestDto;
import com.matkap.ecommerce.model.user.SiteUser;
import com.matkap.ecommerce.service.user.SiteUserService;
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
    public ResponseEntity<SiteUser> createSiteUser(@RequestBody SiteUserRequestDto siteUserRequestDto){
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

    public ResponseEntity<SiteUser> deleteSiteUser(@PathVariable Long id){
        SiteUser siteUser = siteUserService.deleteSiteUser(id);
        return new ResponseEntity<>(siteUser, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<SiteUser> editSiteUser(@PathVariable Long id,
                                                 @RequestBody SiteUserRequestDto siteUserRequestDto){
        SiteUser siteUser = siteUserService.editSiteUser(id, siteUserRequestDto);
        return new ResponseEntity<>(siteUser, HttpStatus.OK);
    }
}

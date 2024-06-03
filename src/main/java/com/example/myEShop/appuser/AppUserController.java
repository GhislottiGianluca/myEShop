package com.example.myEShop.appuser;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/appuser")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService){this.appUserService = appUserService;}
    @GetMapping
    public AppUserDTO getUserInformation(){
        return appUserService.getAppUserInformation();
    }

    @DeleteMapping
    public void deleteAppUser(){
        appUserService.deleteAppUser();
    }

    @PutMapping
    public void updateAppUser(@RequestBody AppUserDTO appUserDTO){
        appUserService.updateAppUser(appUserDTO);
    }
}

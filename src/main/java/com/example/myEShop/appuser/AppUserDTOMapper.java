package com.example.myEShop.appuser;

import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class AppUserDTOMapper implements Function<AppUser, AppUserDTO> {

    @Override
    public AppUserDTO apply(AppUser appUser){
        return new AppUserDTO(appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getEmail());
    }
}

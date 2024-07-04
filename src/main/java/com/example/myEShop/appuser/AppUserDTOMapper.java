package com.example.myEShop.appuser;

import java.util.function.Function;
import org.springframework.stereotype.Service;

/**
 * Service class for mapping {@link AppUser} entities to {@link AppUserDTO} data transfer objects.
 * <p>
 * This class implements the {@link Function} interface to provide a method
 * for converting an AppUser entity to an AppUserDTO.
 * </p>
 */
@Service
public class AppUserDTOMapper implements Function<AppUser, AppUserDTO> {

    /**
     * Applies this function to the given argument.
     * <p>
     * This method converts an {@link AppUser} entity to an {@link AppUserDTO}.
     * </p>
     *
     * @param appUser the AppUser entity to convert
     * @return the converted AppUserDTO
     */
    @Override
    public AppUserDTO apply(AppUser appUser) {
        return new AppUserDTO(appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getEmail());
    }
}

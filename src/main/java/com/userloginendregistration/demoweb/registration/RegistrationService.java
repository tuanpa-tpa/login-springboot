package com.userloginendregistration.demoweb.registration;

import com.userloginendregistration.demoweb.appuser.AppUser;
import com.userloginendregistration.demoweb.appuser.AppUserRole;
import com.userloginendregistration.demoweb.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(
                new AppUser(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
    public List<AppUser> findAll() {
        return appUserService.findAll();
    }

}

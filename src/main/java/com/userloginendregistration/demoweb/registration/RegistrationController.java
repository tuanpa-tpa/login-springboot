package com.userloginendregistration.demoweb.registration;

import com.userloginendregistration.demoweb.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping("/list")
    public List<AppUser> findAll() {
        return registrationService.findAll();
    }
}

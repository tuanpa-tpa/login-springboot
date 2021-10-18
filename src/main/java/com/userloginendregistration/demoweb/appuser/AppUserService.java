package com.userloginendregistration.demoweb.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()
                        -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExits = appUserRepository.findByEmail(appUser.getEmail())
                .isPresent();
        if (userExits) {
            throw new IllegalStateException("email already token");
        }

        String encodePassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodePassword);
        appUserRepository.save(appUser);
        return "is works";
    }

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }


}

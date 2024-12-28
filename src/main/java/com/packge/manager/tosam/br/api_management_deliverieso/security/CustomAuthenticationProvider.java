package com.packge.manager.tosam.br.api_management_deliverieso.security;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.User;
import com.packge.manager.tosam.br.api_management_deliverieso.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var login = authentication.getName();

        var password = (String) authentication.getCredentials();

        Optional<User> userOptional = userService.GetDetailsEmail(login);

        User user = userOptional.get();

        boolean PasswordsMatch =  passwordEncoder.matches(password, user.getPassword());

        if (PasswordsMatch){

            return new CustomAutenticatiion(user);

        }



        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}

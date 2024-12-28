package com.packge.manager.tosam.br.api_management_deliverieso.security;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.User;
import com.packge.manager.tosam.br.api_management_deliverieso.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomUserDatailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userService.GetDetailsEmail(username);

        if (user.isEmpty()){

            throw new UsernameNotFoundException("Usuario não encontrado!");
        }



        return org.springframework.security.core.userdetails.User.
                builder().username(user.get().getEmail())
                .password(user.get().getPassword())
                .roles(user.get().getAccessType().toArray(new String[user.get().getAccessType().size()])).build();
    }
}

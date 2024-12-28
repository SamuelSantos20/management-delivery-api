package com.packge.manager.tosam.br.api_management_deliverieso.security;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class CustomAutenticatiion implements Authentication {

    private final User user;


    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return user.getAccessType().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            throw new IllegalArgumentException("Não é necessario chamar o metodo!");
    }

    @Override
    public String getName() {
        return user.getEmail();
    }
}

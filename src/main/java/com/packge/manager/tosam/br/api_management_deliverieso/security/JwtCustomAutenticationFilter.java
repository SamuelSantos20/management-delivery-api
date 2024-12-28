package com.packge.manager.tosam.br.api_management_deliverieso.security;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.User;
import com.packge.manager.tosam.br.api_management_deliverieso.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtCustomAutenticationFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (mustConvert(authentication)){

            String login  = authentication.getName();

            Optional<User> userOptional = userService.GetDetailsEmail(login);

            System.out.println(userOptional.get());

            if (userOptional.isPresent()){

                authentication = new CustomAutenticatiion(userOptional.get());


                SecurityContextHolder.getContext().setAuthentication(authentication);

            }


        }

        filterChain.doFilter(request,response);

    }

    private boolean mustConvert(Authentication authentication) {

        return authentication!=null && authentication instanceof JwtAuthenticationToken;
    }

}

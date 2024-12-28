package com.packge.manager.tosam.br.api_management_deliverieso.security;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.User;
import com.packge.manager.tosam.br.api_management_deliverieso.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SocialLoginSucessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private static final String DEFAULT_PASSWORD = "1234";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = oAuth2AuthenticationToken.getPrincipal();
        String login = oAuth2User.getAttribute("email");

        Optional<User> user = userService.GetDetailsEmail(login);

        if (user.isEmpty()){

            user = Optional.of(CreateUser(login));
        }
        User userFound = user.get();
        System.out.println(user.get());
        CustomAutenticatiion customAutenticatiion = new CustomAutenticatiion(userFound);

        SecurityContextHolder.getContext().setAuthentication(customAutenticatiion);

        super.onAuthenticationSuccess(request, response, customAutenticatiion);
    }

    private User CreateUser(String login) {

        User  user = new User();

        String encode = passwordEncoder.encode(DEFAULT_PASSWORD);

        user.setPassword(encode);
        user.setUsername(GetUser(login));
        user.setEmail(login);
        user.setAccessType(List.of("ADMINISTRATOR"));

        userService.save(user);

        return user;
    }

    private String GetUser(String login) {

        return login.substring(0, login.indexOf("@"));
    }
}

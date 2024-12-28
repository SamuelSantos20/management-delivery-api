package com.packge.manager.tosam.br.api_management_deliverieso.config;

import com.packge.manager.tosam.br.api_management_deliverieso.security.JwtCustomAutenticationFilter;
import com.packge.manager.tosam.br.api_management_deliverieso.security.SocialLoginSucessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity httpSecurity, SocialLoginSucessHandler loginSucessHandler, JwtCustomAutenticationFilter jwtCustomAutenticationFilter) throws Exception {

        return httpSecurity.csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(maches ->
                        maches.requestMatchers("/login").permitAll()
                                .requestMatchers("/address/**").permitAll()
                                .requestMatchers("/customer/**").permitAll()
                                .requestMatchers("/deliveryMan/**").permitAll()
                                .requestMatchers("/orders/**").permitAll()
                                .requestMatchers("/route/**").permitAll()
                                .requestMatchers("/users/**").permitAll()
                                .requestMatchers("/client/**").permitAll()
                                .anyRequest().authenticated())
                .oauth2Login(httpSecurityOAuth2LoginConfigurer -> {

                    httpSecurityOAuth2LoginConfigurer.successHandler(loginSucessHandler).loginPage("/login");
                }).oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer ->
                {
                    httpSecurityOAuth2ResourceServerConfigurer.jwt(Customizer.withDefaults());
                })

                .addFilterAfter(jwtCustomAutenticationFilter, BearerTokenAuthenticationFilter.class).build();

    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return web -> web.ignoring().requestMatchers("/v2/api-docs/**",
                "/v3/api-docs/**",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/webjars/**");
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {

        return new GrantedAuthorityDefaults("");

    }


    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {

        var authenticationConverter = new JwtGrantedAuthoritiesConverter();

        authenticationConverter.setAuthorityPrefix("");
        var convert = new JwtAuthenticationConverter();

        convert.setJwtGrantedAuthoritiesConverter(authenticationConverter);
        return convert;
    }

}

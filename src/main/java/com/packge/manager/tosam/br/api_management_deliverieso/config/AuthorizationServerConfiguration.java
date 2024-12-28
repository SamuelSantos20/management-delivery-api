package com.packge.manager.tosam.br.api_management_deliverieso.config;

import com.packge.manager.tosam.br.api_management_deliverieso.security.CustomAutenticatiion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class AuthorizationServerConfiguration {

    @Bean
    @Order(1)
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);

        httpSecurity.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());


        httpSecurity.oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer ->
                httpSecurityOAuth2ResourceServerConfigurer.jwt(Customizer.withDefaults()));

        httpSecurity.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/login"));

        return httpSecurity.build();


    }

    @Bean
    TokenSettings tokenSettings() {

        return TokenSettings.builder().accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                .accessTokenTimeToLive(Duration.ofMinutes(180))
                .refreshTokenTimeToLive(Duration.ofMinutes(240))
                .build();
    }


    @Bean
    ClientSettings clientSettings() {
        return ClientSettings.builder()
                .requireAuthorizationConsent(false)
                .build();

    }

    @Bean
    AuthorizationServerSettings authorizationServerSettings(){

        return  AuthorizationServerSettings.builder()
                .tokenEndpoint("/oauth2/token")
                .tokenIntrospectionEndpoint("/oauth2/introspect")
                .tokenRevocationEndpoint("/oauth2/revoke")
                .authorizationEndpoint("/oauth2/authorize")
                .oidcUserInfoEndpoint("/oauth2/userinfo")
                .jwkSetEndpoint("/oauth2/jwks")
                .oidcLogoutEndpoint("/oauth2/logout")
                .build();


    }

    @Bean
    OAuth2TokenCustomizer<JwtEncodingContext> jwtEncodingContextOAuth2TokenCustomizer(){

        return  context -> {


          var principal  = context.getPrincipal();


          if (principal instanceof CustomAutenticatiion customAutenticatiion){

              OAuth2TokenType oAuth2TokenType = context.getTokenType();

              if (OAuth2TokenType.ACCESS_TOKEN.equals(oAuth2TokenType)){

                  Collection<GrantedAuthority> authorities = customAutenticatiion.getAuthorities();

                  List<String> stringList = authorities.stream().map(GrantedAuthority:: getAuthority).toList();

                  context.getClaims().claim("authorities", stringList).claim("email", customAutenticatiion.getUser().getEmail());

              }

          }

        };

    }

    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder(10);
    }

}

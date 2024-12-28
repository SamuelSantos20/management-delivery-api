package com.packge.manager.tosam.br.api_management_deliverieso.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.packge.manager.tosam.br.api_management_deliverieso.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomRegisteredClientRepository implements RegisteredClientRepository {

    private final ClientService clientService;

    private final TokenSettings tokenSettings;

    private final ClientSettings clientSettings;



    @Override
    public void save(RegisteredClient registeredClient) {}

    @Override
    public RegisteredClient findById(String id) {return null;}

    @Override
    public RegisteredClient findByClientId(String clientId) {

        System.out.println(clientId);
        var  client = clientService.GetByClientId(clientId);

        System.out.println(client.get());


        if (client.isEmpty()){

            return null;
        }

        return RegisteredClient.withId(client.get().getClientId().toString())
                .clientId(client.get().getClientId())
                .clientSecret(client.get().getClientSecrete())
                .redirectUri(client.get().getRedirectURI())
                .scope(client.get().getScope())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .clientSettings(clientSettings)
                .tokenSettings(tokenSettings)
                .build();


    }

    @Bean
    JWKSource<SecurityContext> JwtSource() throws Exception {

        RSAKey rsaKey = generateRSAKey();

        JWKSet jwkSet = new JWKSet(rsaKey);

        return new ImmutableJWKSet<>(jwkSet);
    }


    private RSAKey generateRSAKey() throws Exception {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();

        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        return new RSAKey.Builder(rsaPublicKey)
                .privateKey(rsaPrivateKey)
                .keyID(UUID.randomUUID()
                .toString())
                .build();


    }


    @Bean
    JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource){


        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }


}

package com.packge.manager.tosam.br.api_management_deliverieso.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Management Delivery API", version = "v.1",contact = @Contact(name = "Samuel Santos Miranda", email = "samuelfamilia377@gmail.com",url = "https://github.com/SamuelSantos20")), security={
@SecurityRequirement(name = "bearerAuth")
})
     @SecurityScheme(name = "bearerAuth",
             type = SecuritySchemeType.HTTP,
             bearerFormat = "JWT",
             scheme = "bearer",
             in = SecuritySchemeIn.HEADER)

public class OpenApiConfiguration {
}
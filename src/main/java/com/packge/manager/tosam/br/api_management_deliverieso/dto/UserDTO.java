package com.packge.manager.tosam.br.api_management_deliverieso.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(name = "UserDTO")
public record UserDTO(
        @NotBlank(message = "O campo login é obrigatório!")
        @Schema(name = "login")
        String login,

        @NotBlank(message = "O campo password é obrigatório!")
        @Schema(name = "password")
        String password,

        @NotNull(message = "O campo accessType é obrigatório!")
        @Schema(name = "accessType")
        List<String> accessType,

        @NotBlank(message = "O campo username é obrigatório!")
        @Schema(name = "username")
        String username
) {
}

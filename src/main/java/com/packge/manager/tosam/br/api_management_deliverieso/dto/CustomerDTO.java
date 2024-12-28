package com.packge.manager.tosam.br.api_management_deliverieso.dto;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Address;
import com.packge.manager.tosam.br.api_management_deliverieso.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;
import java.util.UUID;
@Schema(name = "CustomerDTO")
public record CustomerDTO(
        @NotBlank(message = "O campo nome é obrigatório!")
        @Schema(name = "name")
        String name,

        @NotBlank(message = "O campo cpf é obrigatório! ")
        @CPF
        @Schema(name = "cpf")
        String cpf,

        @NotBlank(message = "O campo telefone é obrigatório!")
        @Schema(name = "telephone")
        String telephone,

        @Schema(name = "user")
        User user,

        @Schema(name = "address")
        Address address
) {
}

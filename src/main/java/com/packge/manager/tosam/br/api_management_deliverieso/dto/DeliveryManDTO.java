package com.packge.manager.tosam.br.api_management_deliverieso.dto;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.StatusDeliveryMan;
import com.packge.manager.tosam.br.api_management_deliverieso.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Schema(name = "DeliveryManDTO")
public record DeliveryManDTO(
        UUID id,
        @NotBlank(message = "O campo nome é obrigatório!")
        @Schema(name = "name")
        String name,

        @CPF(message = "O CPF digitado é invalido!")
        @NotBlank(message = "O campo cpf é obrigatório")
        @Schema(name = "cpf")
        String cpf,

        @NotBlank(message = "O campo telefone é obrigatório")
        @Schema(name = "telephone")
        String telephone,

        @NotNull(message = "O campo status é obrigatório")
        @Schema(name = "statusDeliveryMan")
        StatusDeliveryMan statusDeliveryMan,

        @Schema(name = "user")
        User user) {


}

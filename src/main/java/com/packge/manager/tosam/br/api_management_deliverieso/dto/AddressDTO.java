package com.packge.manager.tosam.br.api_management_deliverieso.dto;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.AddressType;
import com.packge.manager.tosam.br.api_management_deliverieso.domain.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(name = "AddressDTO")
public record AddressDTO(
        @NotBlank(message = "O campo street é obrigatorio!")
        @Schema(name = "street")
        String street,
        @NotNull(message = "O campo number é obrigatório!")
        @Schema(name = "number")
        Integer number,
        @NotBlank(message = "O campo city é obrigatório!")
        @Schema(name = "city")
        String city,
        @NotBlank(message = "O campo neighborhood é obrigaório!")
        @Schema(name = "neighborhood")
        String neighborhood,
        @NotBlank(message = "O campo complement é obrigatório!")
        @Schema(name = "complement")
        String complement,
        @Schema(name = "customers")
        List<Customer> customers,
        @Schema(name = "addressType")
        AddressType addressType) {
}

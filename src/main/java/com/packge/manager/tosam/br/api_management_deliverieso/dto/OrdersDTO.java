package com.packge.manager.tosam.br.api_management_deliverieso.dto;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Address;
import com.packge.manager.tosam.br.api_management_deliverieso.domain.Customer;
import com.packge.manager.tosam.br.api_management_deliverieso.domain.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(name = "OrdersDTO")
public record OrdersDTO(
        @NotNull(message = "O campo de cliente é obrigatório!")
        @Schema(name = "customer")
        Customer customer,

        @NotNull(message = "O campo peso do produto é obrigatório!")
        @Schema(name = "weight")
        Double weight,

        @NotNull(message = "O campo de status da entrega é obrigatório!")
        @Schema(name = "orderStatus")
        OrderStatus orderStatus,

        @Schema(name = "orderCode")
        UUID orderCode,

        @NotNull(message = "O campo de origem da entrega é obrigatório!")
        @Schema(name = "origin")
        Address origin) {
}

package com.packge.manager.tosam.br.api_management_deliverieso.dto;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.DeliveryMan;
import com.packge.manager.tosam.br.api_management_deliverieso.domain.Orders;
import com.packge.manager.tosam.br.api_management_deliverieso.domain.StatusRoute;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Schema(name = "RouteDTO")
public record RouteDTO(

        @NotNull(message = "O campo date é obrigatório!")
        @Schema(name = "date")
        LocalDateTime date,

        @NotNull(message = "O campo deliveryMan é obrigatório! ")
        @Schema(name = "deliveryMan")
        DeliveryMan deliveryMan,

        @NotNull(message = "O campo orders é obrigatóro!")
        @Schema(name = "orders")
        List<Orders> orders,

        @NotNull(message = "O campo statusRoute é obrigatório!")
        @Schema(name = "statusRoute")
        StatusRoute statusRoute) {
}

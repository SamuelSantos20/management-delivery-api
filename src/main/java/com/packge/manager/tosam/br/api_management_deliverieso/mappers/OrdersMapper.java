package com.packge.manager.tosam.br.api_management_deliverieso.mappers;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Orders;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.OrdersDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    Orders toEntity(OrdersDTO ordersDTO);

    OrdersDTO toDTO(Orders orders);


}

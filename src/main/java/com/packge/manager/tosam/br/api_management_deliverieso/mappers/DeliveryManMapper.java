package com.packge.manager.tosam.br.api_management_deliverieso.mappers;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.DeliveryMan;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.DeliveryManDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryManMapper {


    public DeliveryMan toEnttity(DeliveryManDTO deliveryManDTO);

    public DeliveryManDTO toDTO(DeliveryMan deliveryMan);

}

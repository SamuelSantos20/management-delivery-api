package com.packge.manager.tosam.br.api_management_deliverieso.mappers;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Client;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.ClientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client CLIENT_Entity(ClientDTO clientDTO);

    ClientDTO CLIENT_DTO(Client client);

}

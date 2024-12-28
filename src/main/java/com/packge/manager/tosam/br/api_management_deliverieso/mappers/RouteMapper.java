package com.packge.manager.tosam.br.api_management_deliverieso.mappers;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Route;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.RouteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper {


    Route toEntity(RouteDTO routeDTO);

    RouteDTO toDTO(Route route);


}

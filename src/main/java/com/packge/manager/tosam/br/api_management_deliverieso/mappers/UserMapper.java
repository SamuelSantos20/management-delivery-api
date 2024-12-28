package com.packge.manager.tosam.br.api_management_deliverieso.mappers;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.User;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    User toDTO(User user);

}

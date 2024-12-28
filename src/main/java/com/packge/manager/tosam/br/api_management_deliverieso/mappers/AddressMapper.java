package com.packge.manager.tosam.br.api_management_deliverieso.mappers;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Address;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.AddressDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    public Address toEntity(AddressDTO addressDTO);

    public AddressDTO toDTO(Address address);


}

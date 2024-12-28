package com.packge.manager.tosam.br.api_management_deliverieso.mappers;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Customer;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.CustomerDTO;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.AddressRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerDTO customerDTO);

  CustomerDTO toDTO(Customer customer);

}

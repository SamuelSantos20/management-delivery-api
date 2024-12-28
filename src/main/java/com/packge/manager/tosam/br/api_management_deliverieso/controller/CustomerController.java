package com.packge.manager.tosam.br.api_management_deliverieso.controller;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Customer;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.CustomerDTO;
import com.packge.manager.tosam.br.api_management_deliverieso.mappers.CustomerMapper;
import com.packge.manager.tosam.br.api_management_deliverieso.service.CustomerService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Tag(name = "Customer")
public class CustomerController implements GenericController {

    private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMINISTRATOR')")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "422",description = "Erro de validação.")

    })
    public ResponseEntity<Object> save(@RequestBody @Valid CustomerDTO customerDTO) {

        Optional<CustomerDTO> customerDTOOptional = Optional.ofNullable(customerDTO);

        var customer = customerMapper.toEntity(customerDTOOptional.get());

        customerService.save(customer);

        URI uri = gerarHandlerURILocation(customer.getId());

        return ResponseEntity.created(uri).build();


    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMINISTRATOR')")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Sucesso."),
            @ApiResponse(responseCode = "404",description = "Não localizado.")
    })
    public ResponseEntity<Object> GetDetails(@PathVariable("id") String id) {

        UUID uuid = UUID.fromString(id);

        Optional<Customer> customer = customerService.GetDetails(uuid);

        if (customer.isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        CustomerDTO dto = customerMapper.toDTO(customer.get());

        return ResponseEntity.ok(dto);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMINISTRATOR')")
    @ApiResponses({


            @ApiResponse(responseCode = "422", description = "Erro de validação."),
            @ApiResponse(responseCode = "204", description = "Atualizado com sucesso.")


    })
    public ResponseEntity<Object> Update(@PathVariable("id") String id, @RequestBody @Valid CustomerDTO customerDTO) {

        UUID uuid = UUID.fromString(id);

        Optional<Customer> customerOptional = customerService.GetDetails(uuid);

        Optional<Customer> customer = Optional.of(customerOptional.get());

        customer.get().setName(customerDTO.name());
        customer.get().setCpf(customerDTO.cpf());
        customer.get().setTelephone(customerDTO.telephone());

        customerService.Update(customer.orElse(null));

        return ResponseEntity.noContent().build();

    }

    //80cd19d2-8044-4456-b667-18e76955d919

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMINISTRATOR')")
    @ApiResponses({

            @ApiResponse(responseCode = "404", description = "Não localizado."),
            @ApiResponse(responseCode = "204",description = "Deletado com sucesso.")

    })
    public ResponseEntity<Object> Delete(@PathVariable("id") String id) {

        UUID uuid = UUID.fromString(id);

        Optional<Customer> customer = customerService.GetDetails(uuid);

        if (customer.isEmpty()) {

            ResponseEntity.notFound().build();

        }

        customerService.Delete(customer.get().getId());


        return ResponseEntity.noContent().build();
    }


}

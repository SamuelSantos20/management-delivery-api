package com.packge.manager.tosam.br.api_management_deliverieso.controller;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Address;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.AddressDTO;
import com.packge.manager.tosam.br.api_management_deliverieso.mappers.AddressMapper;
import com.packge.manager.tosam.br.api_management_deliverieso.service.AddressService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
@Tag(name = "Address")
public class AddressController implements GenericController {

    private final AddressService addressService;

    private final AddressMapper addressMapper;


    @PostMapping
    @PreAuthorize(("hasAnyRole('ADMINISTRATOR', 'DELIVERYMAN', 'CUSTOMER' )"))
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "422",description = "Erro de validação.")

    })
    public ResponseEntity<Object> save(@RequestBody @Valid AddressDTO addressDTO) {

        Optional<AddressDTO> addressDTOOptional = Optional.ofNullable(addressDTO);

        var entityAddress = addressMapper.toEntity(addressDTOOptional.get());

        addressService.save(entityAddress);

        URI uri = gerarHandlerURILocation(entityAddress.getId());

        return ResponseEntity.created(uri).build();

    }


    @GetMapping
    @PreAuthorize(("hasAnyRole('ADMINISTRATOR', 'DELIVERYMAN', 'CUSTOMER' )"))
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Sucesso.")

    })
    public ResponseEntity<Object> Search(@RequestParam(value = "id", required = false) String id,
                                         @RequestParam(value = "street", required = false) String street,
                                         @RequestParam(value = "number", required = false) Integer number,
                                         @RequestParam(value = "city", required = false) String city,
                                         @RequestParam(value = "neighborhood", required = false) String neighborhood,
                                         @RequestParam(value = "complement", required = false) String complement,
                                         @RequestParam(value = "customer_id", required = false) String customer_id,
                                         @RequestParam(value = "numberPage", required = false) Integer numberPage,
                                         @RequestParam(value = "numberElements", required = false) Integer numberElements) {


        Page<Address> addresses = addressService.addressSearch(id, street, number, city, neighborhood,
                complement, customer_id, numberPage, numberElements);

        Page<AddressDTO> addressDTOS = addresses.map(addressMapper::toDTO);


        return ResponseEntity.ok(addressDTOS);

    }

    @GetMapping("/{id}")
    @PreAuthorize(("hasAnyRole('ADMINISTRATOR', 'DELIVERYMAN', 'CUSTOMER' )"))
    @ApiResponses({

            @ApiResponse(responseCode = "404",description = "Não localizado."),
            @ApiResponse(responseCode = "200", description = "Sucesso.")

    })
    public ResponseEntity<Object> GetDetails(@PathVariable("id") String id) {

        UUID uuid = UUID.fromString(id);

        Optional<Address> addressOptional = addressService.GetDetails(uuid);

        if (addressOptional.isEmpty()) {

            return ResponseEntity.notFound().build();

        }

        Optional<AddressDTO> addressDTO = addressOptional.map(addressMapper::toDTO);

        return ResponseEntity.ok(addressDTO.get());

    }

    @PutMapping("/{id}")
    @PreAuthorize(("hasAnyRole('ADMINISTRATOR', 'DELIVERYMAN', 'CUSTOMER' )"))
    @ApiResponses({

            @ApiResponse(responseCode = "422",description = "Erro de validação."),
            @ApiResponse(responseCode = "204",description = "Atualizado com sucesso."),

    })
    public ResponseEntity<Object> Update(@PathVariable("id") String id, @RequestBody @Valid AddressDTO addressDTO) {

        UUID uuid = UUID.fromString(id);

        Optional<Address> addressOptional = Optional.ofNullable(addressService.GetDetails(uuid).orElseGet(() -> null));

        Address address = addressOptional.get();

        address.setAddressType(addressDTO.addressType());
        address.setCity(addressDTO.city());
        address.setNumber(addressDTO.number());
        address.setStreet(addressDTO.street());
        address.setNeighborhood(addressDTO.neighborhood());
        address.setComplement(addressDTO.complement());

        addressService.Update(address);


        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    @PreAuthorize(("hasAnyRole('ADMINISTRATOR', 'DELIVERYMAN', 'CUSTOMER' )"))
    @ApiResponses({

            @ApiResponse(responseCode = "404", description = "Endereço não localizado."),
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso."),

    })
    public ResponseEntity<Object> Delete(@PathVariable("id") String id) {

        UUID uuid = UUID.fromString(id);

        Optional<Address> address = Optional.ofNullable(addressService.GetDetails(uuid).orElseGet(() -> null));

        if (address.isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        addressService.Delete(address.get());


        return ResponseEntity.noContent().build();

    }




}

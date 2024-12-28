package com.packge.manager.tosam.br.api_management_deliverieso.controller;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.DeliveryMan;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.DeliveryManDTO;
import com.packge.manager.tosam.br.api_management_deliverieso.mappers.DeliveryManMapper;
import com.packge.manager.tosam.br.api_management_deliverieso.service.DeliveryManService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/deliveryMan")
@RequiredArgsConstructor
@Tag(name = "Delivery")
public class DeliveryManController implements GenericController{

    private final DeliveryManMapper deliveryManMapper;

    private final DeliveryManService deliveryManService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({

            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação.")
    })
    public ResponseEntity<Object> save( @RequestBody @Valid DeliveryManDTO deliveryManDTO) {

        var entity = deliveryManMapper.toEnttity(deliveryManDTO);

        deliveryManService.save(entity);

        var location = gerarHandlerURILocation(deliveryManDTO.id());

        return ResponseEntity.created(location).build();

    }


    @PutMapping("/{id}")
    @PreAuthorize(("hasRole('ADMINISTRATOR')"))
    @ApiResponses({

            @ApiResponse(responseCode = "204", description = "Atualizado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação.")
    })
    public ResponseEntity<Object> update(@PathVariable("id") String id , @RequestBody @Valid DeliveryManDTO deliveryManDTO) {

        Optional<DeliveryMan> deliveryManOptional = deliveryManService.GetDetails(UUID.fromString(id));

        var deliveryMan = deliveryManOptional.get();

        deliveryMan.setName(deliveryManDTO.name());
        deliveryMan.setCpf(deliveryManDTO.cpf());
        deliveryMan.setTelephone(deliveryManDTO.telephone());
        deliveryMan.setStatusDeliveryMan(deliveryManDTO.statusDeliveryMan());

        deliveryManService.Update(deliveryMan);


        return ResponseEntity.noContent().build();


    }


    @GetMapping("/{id}")
    @PreAuthorize(("hasRole('ADMINISTRATOR')"))
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Sucesso."),
            @ApiResponse(responseCode = "404", description = "Não localizado.")

    })
    public ResponseEntity<Object> getDetails(@PathVariable("id") String id) {

        Optional<DeliveryMan> deliveryMan = deliveryManService.GetDetails(UUID.fromString(id));

        return deliveryMan.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());


    }

    @DeleteMapping
    @PreAuthorize(("hasRole('ADMINISTRATOR')"))
    @ApiResponses({

            @ApiResponse(responseCode = "204", description = "Deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Não localizado.")

    })
    public ResponseEntity<Object> Delete(@RequestParam("id") String id) {

        Optional<DeliveryMan> deliveryMan = Optional.ofNullable(deliveryManService.GetDetails(UUID.fromString(id)).orElseGet(() -> null));

        if (deliveryMan.isEmpty()){

            return ResponseEntity.notFound().build();
        }

        deliveryManService.Delete(deliveryMan.get());

        return ResponseEntity.noContent().build();


    }






}

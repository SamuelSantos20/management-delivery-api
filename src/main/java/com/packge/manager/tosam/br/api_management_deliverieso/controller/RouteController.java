package com.packge.manager.tosam.br.api_management_deliverieso.controller;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Route;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.RouteDTO;
import com.packge.manager.tosam.br.api_management_deliverieso.mappers.RouteMapper;
import com.packge.manager.tosam.br.api_management_deliverieso.service.RouteService;
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
@RequestMapping("/route")
@RequiredArgsConstructor
@Tag(name = "Route")
public class RouteController implements GenericController {

    private final RouteService routeService;

    private final RouteMapper routeMapper;

    @PostMapping
    @PreAuthorize(("hasAnyRole('ADMINISTRATOR', 'DELIVERYMAN')"))
    @ApiResponses({

            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação.")
    })
    public ResponseEntity<Object> save(@RequestBody @Valid RouteDTO routeDTO) {

        Route entity = routeMapper.toEntity(routeDTO);

        routeService.save(entity);

        URI uri = gerarHandlerURILocation(entity.getId());

        return ResponseEntity.created(uri).build();


    }

    @GetMapping("/{id}")
    @PreAuthorize(("hasAnyRole('ADMINISTRATOR', 'DELIVERYMAN')"))
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Sucesso."),
            @ApiResponse(responseCode = "404", description = "Não localizado.")

    })
    public ResponseEntity<Object> GetDetails(@PathVariable("id") String id) {

        UUID uuid = UUID.fromString(id);

        Optional<Route> routeOptional = routeService.GetDetails(uuid);

        if (routeOptional.isEmpty()) {

            return ResponseEntity.notFound().build();

        }

        Route route = routeOptional.get();

        RouteDTO dto = routeMapper.toDTO(route);

        return ResponseEntity.ok(dto);


    }

    @PutMapping("/{id}")
    @PreAuthorize(("hasAnyRole('ADMINISTRATOR', 'DELIVERYMAN')"))
    @ApiResponses({

            @ApiResponse(responseCode = "204", description = "Atualizado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação.")
    })
    public ResponseEntity<Object> Update(@PathVariable("id") String id, @RequestBody RouteDTO routeDTO) {
        UUID uuid = UUID.fromString(id);

        Optional<Route> routeOptional = routeService.GetDetails(uuid);

        Route route = routeOptional.get();

        route.setStatusRoute(routeDTO.statusRoute());
        route.setDate(routeDTO.date());
        route.setOrders(routeDTO.orders());
        route.setDeliveryMan(routeDTO.deliveryMan());

        routeService.Upate(route);

        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("/{id}")
    @PreAuthorize(("hasAnyRole('ADMINISTRATOR', 'DELIVERYMAN')"))
    @ApiResponses({

            @ApiResponse(responseCode = "204", description = "Deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Não localizado.")

    })
    public ResponseEntity<Object> Delete(@PathVariable("id") String id) {

        UUID uuid = UUID.fromString(id);

        Optional<Route> routeOptional = routeService.GetDetails(uuid);

        if (routeOptional.isEmpty()) {

            return ResponseEntity.notFound().build();

        }

        Route route = routeOptional.get();

        routeService.Delete(route.getId());

        return ResponseEntity.noContent().build();

    }


}

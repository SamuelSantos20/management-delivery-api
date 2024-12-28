package com.packge.manager.tosam.br.api_management_deliverieso.controller;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Orders;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.OrdersDTO;
import com.packge.manager.tosam.br.api_management_deliverieso.mappers.OrdersMapper;
import com.packge.manager.tosam.br.api_management_deliverieso.service.OrdersService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Orders")
public class OrdersController implements GenericController {

    private final OrdersService ordersService;

    private final OrdersMapper ordersMapper;

    @PostMapping
    @PreAuthorize(("hasAnyRole('ADMINISTRATOR', 'DELIVERYMAN', 'CUSTOMER' )"))
    @ApiResponses({

            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação.")
    })
    public ResponseEntity<Object> save(@RequestBody @Valid OrdersDTO ordersDTO) {

        Optional<OrdersDTO> orders = Optional.ofNullable(ordersDTO);

        var order = orders.get();

        Orders entity = ordersMapper.toEntity(order);

        ordersService.save(entity);

        URI uri = gerarHandlerURILocation(entity.getId());

        return ResponseEntity.created(uri).build();

    }

    @GetMapping("/{id}")
    @PreAuthorize(("hasAnyRole('ADMINISTRATOR', 'DELIVERYMAN', 'CUSTOMER' )"))
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Sucesso."),
            @ApiResponse(responseCode = "404", description = "Não localizado.")

    })
    public ResponseEntity<Object> GetDetails(@PathVariable("id") String id) {

        UUID uuid = UUID.fromString(id);

        Optional<Orders> orders = ordersService.GetDetails(uuid);

        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();

        }

        var order = ordersMapper.toDTO(orders.get());

        return ResponseEntity.ok(order);

    }

    @PutMapping("/{id}")
    @PreAuthorize(("hasAnyRole('ADMINISTRATOR', 'DELIVERYMAN', 'CUSTOMER' )"))
    @ApiResponses({

            @ApiResponse(responseCode = "204", description = "Atualizado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação.")
    })
    public ResponseEntity<Object> Update(@PathVariable("id") String id, @RequestBody @Valid OrdersDTO ordersDTO) {

        UUID uuid = UUID.fromString(id);

        Optional<Orders> orders = ordersService.GetDetails(uuid);

        var order = orders.get();

        order.setOrderStatus(ordersDTO.orderStatus());
        order.setWeight(ordersDTO.weight());
        order.setCustomer(ordersDTO.customer());
        order.setOrderCode(ordersDTO.orderCode());

        ordersService.Update(order);

        return ResponseEntity.noContent().build();


    }

    @DeleteMapping("/{id}")
    @PreAuthorize(("hasRole('ADMINISTRATOR')"))
    @ApiResponses({

            @ApiResponse(responseCode = "204", description = "Deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Não localizado.")

    })
    public ResponseEntity<Object> Delete(@PathVariable("id") String id) {
        UUID uuid = UUID.fromString(id);

        Optional<Orders> orders = ordersService.GetDetails(uuid);

        if (orders.isEmpty()) {

            return ResponseEntity.notFound().build();

        }

        var order = orders.get();

        ordersService.Delete(order.getId());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Sucesso.")

    })
    public ResponseEntity<Object> OrderSearch(
            @RequestParam(name = "weight", required = false) Double weight,

            @RequestParam(name = "orderStatus", required = false) String orderStatus,

            @RequestParam(name = "orderCode", required = false) UUID orderCode,

            @RequestParam(name = "dayCreation", required = false) Integer dayCreation,

            @RequestParam(name = "monthCreation", required = false) Integer monthCreation,

            @RequestParam(name = "yearCreation", required = false) Integer yearCreation,

            @RequestParam(name = "name", required = false) String name,

            @RequestParam(name = "cpf", required = false) String cpf,

            @RequestParam(name = "numberPage", defaultValue = "0") Integer numberPage,

            @RequestParam(name = "quantityPage", defaultValue= "1") Integer quantityPage) {


        System.out.println(name);
        System.out.println(yearCreation);
        Page<Orders> ordersPage = ordersService.OrderSearch(weight, orderStatus, orderCode, dayCreation, monthCreation, yearCreation, name, cpf, numberPage, quantityPage);

        Page<OrdersDTO> ordersSearchDTOS = ordersPage.map(ordersMapper::toDTO);


        System.out.println(ordersSearchDTOS);

        return ResponseEntity.ok(ordersSearchDTOS);


    }


}

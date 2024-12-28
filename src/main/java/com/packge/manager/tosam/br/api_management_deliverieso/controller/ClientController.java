package com.packge.manager.tosam.br.api_management_deliverieso.controller;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Client;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.ClientDTO;
import com.packge.manager.tosam.br.api_management_deliverieso.mappers.ClientMapper;
import com.packge.manager.tosam.br.api_management_deliverieso.service.ClientService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Tag(name = "Client")
public class ClientController {

    private final ClientService clientService;

    private final ClientMapper clientMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(("hasRole('ADMINISTRATOR')"))
    @ApiResponses({

            @ApiResponse(responseCode = "201", description = "criado com sucesso.")
    })
    public void save(@RequestBody ClientDTO clientDTO) {
        System.out.println(clientDTO);
        Client client = clientMapper.CLIENT_Entity(clientDTO);
        clientService.save(client);

    }




}

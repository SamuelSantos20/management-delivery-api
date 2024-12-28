package com.packge.manager.tosam.br.api_management_deliverieso.controller;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.User;
import com.packge.manager.tosam.br.api_management_deliverieso.dto.UserDTO;
import com.packge.manager.tosam.br.api_management_deliverieso.mappers.UserMapper;
import com.packge.manager.tosam.br.api_management_deliverieso.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "User")
public class UserController implements  GenericController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping
    @ApiResponses({

            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação.")
    })
    public ResponseEntity<Object> save(@RequestBody @Valid UserDTO userDTO) {

        System.out.println(userDTO.password());

        User  user = userMapper.toEntity(userDTO);

        userService.save(user);

        URI uri = gerarHandlerURILocation(user.getId());


        return ResponseEntity.created(uri).build();
    }


}

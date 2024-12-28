package com.packge.manager.tosam.br.api_management_deliverieso.domain;

import com.packge.manager.tosam.br.api_management_deliverieso.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientTest {

    @Autowired
    private ClientService clientService;

    @Test
    void GetClientId() {
        var clientId = "client1";

        Optional<Client> client = clientService.GetByClientId(clientId);

        System.out.println(client.get());


    }

}
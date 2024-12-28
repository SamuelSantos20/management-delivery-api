package com.packge.manager.tosam.br.api_management_deliverieso.service;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Client;
import com.packge.manager.tosam.br.api_management_deliverieso.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final PasswordEncoder passwordEncoder;


    public void save(Client client) {

        String encode = passwordEncoder.encode(client.getClientSecrete());

        client.setClientSecrete(encode);

        clientRepository.save(client);
    }

    public Optional<Client>  GetByClientId(String client) {

        return clientRepository.findByClientId(client);

    }


}

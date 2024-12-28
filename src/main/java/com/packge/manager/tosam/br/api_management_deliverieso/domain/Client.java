package com.packge.manager.tosam.br.api_management_deliverieso.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "clientId")
    private String clientId;

    @Column(name = "clientSecrete")
    private String clientSecrete;

    @Column(name = "url")
    private String redirectURI;

    @Column(name = "scope")
    private String scope;

}

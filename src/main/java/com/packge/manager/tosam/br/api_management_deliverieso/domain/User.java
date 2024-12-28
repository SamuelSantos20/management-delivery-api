package com.packge.manager.tosam.br.api_management_deliverieso.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private UUID id;

    @Column(name = "email", length = 200, unique = true, nullable = false, updatable = true)
    private String email;

    @Column(name = "password", length = 200, unique = false, updatable = true, nullable = false)
    private String password;

    @Column(name = "accessType", columnDefinition = "varchar []", nullable = false, unique = false, updatable = true)
    private List<String> accessType;

    @Column(name = "username")
    private String username;

}

package com.packge.manager.tosam.br.api_management_deliverieso.domain;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "deliveryman")
@Data
@EntityListeners(AuditingEntityListener.class)
public class DeliveryMan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private UUID id;

    @Column(name = "name", length = 200, updatable = true, nullable = false)
    private String name;

    @Column(name = "cpf", length = 11, updatable = false, unique = true, nullable = false)
    private String cpf;

    @Column(name = "telephone", length = 13, unique = true, updatable = true, nullable = false)
    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, unique = false, updatable = true)
    private StatusDeliveryMan statusDeliveryMan;

    @CreatedDate
    @Column(name = "registration_data")
    private LocalDateTime registration_data;

    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime update_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}

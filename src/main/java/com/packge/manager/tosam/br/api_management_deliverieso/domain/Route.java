package com.packge.manager.tosam.br.api_management_deliverieso.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "route")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToOne
    private DeliveryMan deliveryMan;

    @OneToMany
    private List<Orders> orders;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusRoute")
    private StatusRoute statusRoute;

    @CreatedDate
    @Column(name = "date_create")
    private LocalDateTime date_create;

    @LastModifiedDate
    @Column(name = "date_update")
    LocalDateTime date_update;


}

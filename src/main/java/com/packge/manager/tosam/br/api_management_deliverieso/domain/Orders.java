package com.packge.manager.tosam.br.api_management_deliverieso.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, unique = false, updatable = true)
    private Customer customer;

    @Column(name = "weight")
    private Double weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "deliveryStatus" , length = 200 ,unique = false , updatable = true , nullable = false)
    private OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address origin;

    @Column(name = "orderCode", unique = true, updatable = false)
    private UUID orderCode;

    @CreatedDate
    @Column(name = "date_created")
    private LocalDateTime date_created;

    @Column(name = "update_date")
    @LastModifiedDate
    private LocalDateTime update_date;


    @PrePersist
    private void generateOrderCode() {
        if (this.orderCode == null) {
            this.orderCode = UUID.fromString(UUID.randomUUID().toString());
        }
    }


}

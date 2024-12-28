package com.packge.manager.tosam.br.api_management_deliverieso.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private UUID id;

    @Column(name = "name", length = 200 , updatable = true , nullable = false)
    private String name;

    @Column(name = "cpf" , length = 11 , updatable = false , unique = true , nullable = false )
    private String cpf;

    @Column(name = "telephone" , length = 13 , unique = true  , updatable = true , nullable = false)
    private String telephone;

    @CreatedDate
    @Column(name = "registration_data")
    private LocalDateTime registration_data;

    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime update_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name = "address")
    @ToString.Exclude
    //@JsonManagedReference
    private Address address;

}

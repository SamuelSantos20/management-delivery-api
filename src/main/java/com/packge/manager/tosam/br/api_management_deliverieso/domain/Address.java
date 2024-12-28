package com.packge.manager.tosam.br.api_management_deliverieso.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id" , unique = true)
    private UUID id;

    @Column(name = "street", length = 200 , unique = false , updatable = true)
    private String street;

    @Column(name = "number" , unique = false , nullable = false)
    private Integer number;

    @Column(name = "city",length = 150 , unique = false , updatable = true)
    private String city;

    @Column(name = "neighborhood", length = 150 , unique = false , updatable = true)
    private String neighborhood;

    @Column(name = "complement",  length = 150, updatable = false, unique = false)
    private String complement;

    @Enumerated(EnumType.STRING)
    @Column(name = "addressType", length = 300, nullable = false , unique = false, updatable = true)
    private AddressType addressType;

    @OneToMany( mappedBy = "address")
    @ToString.Exclude
    //@JsonBackReference
    @JsonIgnore
    private List<Customer> customers;


}

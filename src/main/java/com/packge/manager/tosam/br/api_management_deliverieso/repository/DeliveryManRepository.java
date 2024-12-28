package com.packge.manager.tosam.br.api_management_deliverieso.repository;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.DeliveryMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeliveryManRepository extends JpaRepository<DeliveryMan , UUID >{
    Optional<DeliveryMan> findByCpf(String cpf);
}

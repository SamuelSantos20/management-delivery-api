package com.packge.manager.tosam.br.api_management_deliverieso.repository;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address , UUID>  , JpaSpecificationExecutor {
   // public List<Address> findByCustomers(UUID customerId);
}

package com.packge.manager.tosam.br.api_management_deliverieso.repository;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrdersRepository extends JpaRepository<Orders , UUID> , JpaSpecificationExecutor {
    //boolean existsByOriginId(UUID id);

    boolean existsByCustomerId(UUID id);

    Optional<Orders> findByOrderCode(UUID orderCode);




        @Query("SELECT o FROM Orders o " +
                "JOIN FETCH o.customer c " +
                "JOIN FETCH c.address ca " +
                "JOIN FETCH o.origin ao " +
                "WHERE o.id = :orderId")
        Optional<Orders> findOrderWithCustomerAndAddresses(@Param("orderId") UUID orderId);



        @Query("SELECT CASE WHEN COUNT(o) > 0 THEN TRUE ELSE FALSE END " +
                "FROM Orders o " +
                "JOIN o.customer c " +
                "WHERE o.id = :orderId")
        boolean existsOrderWithCustomerAndAddresses(@Param("orderId") UUID orderId);



}

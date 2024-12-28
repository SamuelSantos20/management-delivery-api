package com.packge.manager.tosam.br.api_management_deliverieso.repository;

import com.packge.manager.tosam.br.api_management_deliverieso.domain.DeliveryMan;
import com.packge.manager.tosam.br.api_management_deliverieso.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RouteRepository extends JpaRepository<Route , UUID> {


                @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END " +
                        "FROM Route r JOIN r.orders o " +
                        "WHERE o.id IN :ordersIds")
                boolean existsByOrdersIds(@Param("ordersIds") List<UUID> ordersIds);



        Optional<Route> findByDateAndDeliveryMan(LocalDateTime date , DeliveryMan deliveryMan);

}

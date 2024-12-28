package com.packge.manager.tosam.br.api_management_deliverieso.repository.specification;


import com.packge.manager.tosam.br.api_management_deliverieso.domain.Orders;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrdersSpecification {


    public static Specification<Orders> specificationCustomer(String name, String cpf) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (name != null) {
                Join<Object,Object> OrdersCustomer = root.join("customer", JoinType.INNER);

                predicates.add(



                        cb.like(cb.upper(OrdersCustomer.get("name")), "%" + name.toUpperCase() + "%")

                );
            }

            if (cpf != null) {

                predicates.add(

                        cb.equal(root.get("customer").get("cpf"), cpf)

                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };


    }


    public static Specification<Orders> specificationWeight(Double weight) {

        return (root, query, cb) -> cb.equal(root.get("weight"), weight);

    }


    public static Specification<Orders> specificationOrderStatus(String orderStatus) {

        return (root, query, cb) -> cb.equal(root.get("orderStatus"), orderStatus.toUpperCase());
    }

    public static Specification<Orders> specificationOrderCode(UUID orderCode) {

        return (root, query, cb) -> cb.equal(root.get("orderCode"), orderCode);
    }

    public static Specification<Orders> specificationDate_created(Integer dayCreation, Integer monthCreation, Integer yearCreation) {

        return (root, query, cb) -> {
            List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();

            if (monthCreation != null) {
                predicates.add(

                        cb.equal(

                                cb.function("to_char", String.class, root.get("date_created"), cb.literal("MM")), monthCreation.toString()

                        )
                );

            }

            if (dayCreation != null) {

                predicates.add(

                        cb.equal(

                                cb.function("to_char", String.class, root.get("date_created"), cb.literal("DD")),   String.format("%02d", Integer.parseInt(dayCreation.toString()))

                        )

                );

            }


            if (yearCreation != null) {

                predicates.add(

                        cb.equal(

                                cb.function("to_char", String.class, root.get("date_created"), cb.literal("YYYY")), yearCreation.toString()

                        )

                );

            }


            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }




}

package com.flystonedev.customer.repository;


import com.flystonedev.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findCustomerByIdAndAuthID (Integer id, String authId);

    long count();

}

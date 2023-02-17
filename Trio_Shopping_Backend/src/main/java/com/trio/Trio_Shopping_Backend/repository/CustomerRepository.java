package com.trio.Trio_Shopping_Backend.repository;

import com.trio.Trio_Shopping_Backend.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {




    Optional<Customer> findByUserName(String username);

    Customer findByMobileNumber(String data);

    Customer findByEmail(String data);
}

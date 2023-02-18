package com.trio.Trio_Shopping_Backend.service;

import com.trio.Trio_Shopping_Backend.domain.Customer;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserInfoService {
    Customer createUser(Customer customer);
    ResponseEntity<?> updateUserDetails(Customer customer);
    Optional<Customer> getUserDetails(long id);
    ResponseEntity<?> deleteAccount(Customer customer);


}

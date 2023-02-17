package com.trio.Trio_Shopping_Backend.service;

import com.trio.Trio_Shopping_Backend.domain.Customer;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface CustomerService {
    Customer createUser(Customer customer);
    ResponseEntity<?> updateUserDetails(Customer customer);
    Optional<Customer> getUserDetails(long id);
    ResponseEntity<?> deleteAccount(Customer customer);

    String addCustomer(Customer customer);
}

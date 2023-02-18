package com.trio.Trio_Shopping_Backend.service;

import com.trio.Trio_Shopping_Backend.domain.Customer;
import com.trio.Trio_Shopping_Backend.repository.CustomerRepository;
import com.trio.Trio_Shopping_Backend.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public Customer createUser(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public ResponseEntity<?> updateUserDetails(Customer customer) {
        return null;
    }

    @Override
    public Optional<Customer> getUserDetails(long id) {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<?> deleteAccount(Customer customer) {
        return null;
    }

}

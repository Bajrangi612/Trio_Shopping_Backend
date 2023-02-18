package com.trio.Trio_Shopping_Backend.service;

import com.trio.Trio_Shopping_Backend.domain.Customer;
import com.trio.Trio_Shopping_Backend.domain.UserInfo;
import org.springframework.http.ResponseEntity;

public interface UserInfoService {
    Customer createUser(Customer customer);
    ResponseEntity<?> updateUserDetails(Customer customer);
    UserInfo getUserDetails(String mobileNumber);
    ResponseEntity<?> deleteAccount(Customer customer);


}

package com.trio.Trio_Shopping_Backend.service;

import com.trio.Trio_Shopping_Backend.domain.Customer;
import com.trio.Trio_Shopping_Backend.domain.UserInfo;
import com.trio.Trio_Shopping_Backend.domain.UserInfoVO;
import org.springframework.http.ResponseEntity;

public interface UserInfoService {
    Customer createUser(Customer customer);
    ResponseEntity<?> updateUserDetails(Customer customer);
    UserInfoVO getUserDetails(String mobileNumber);
    ResponseEntity<?> deleteAccount(Customer customer);


}

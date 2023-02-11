package com.trio.Trio_Shopping_Backend.service;

import com.trio.Trio_Shopping_Backend.domain.UserDetails;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserDetailsService {
    UserDetails createUser(UserDetails userDetails);
    ResponseEntity<?> updateUserDetails(UserDetails userDetails);
    Optional<UserDetails> getUserDetails(long id);
    ResponseEntity<?> deleteAccount(UserDetails userDetails);



}

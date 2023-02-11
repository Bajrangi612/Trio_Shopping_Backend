package com.trio.Trio_Shopping_Backend.service;

import com.trio.Trio_Shopping_Backend.domain.UserDetails;
import com.trio.Trio_Shopping_Backend.repository.UserDetailsRepository;
import com.trio.Trio_Shopping_Backend.responce.ResponseDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails createUser(UserDetails userDetails) {
           return userDetailsRepository.save(userDetails);
        }



    @Override
    public ResponseEntity<?> updateUserDetails(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
        return ResponseDomain.putResponse("Account updated successfully..");
    }

    @Override
    public Optional<UserDetails> getUserDetails(long id) {
        Optional<UserDetails> userDetails = userDetailsRepository.findById(id);
        return userDetails;
    }

    @Override
    public ResponseEntity<?> deleteAccount(UserDetails userDetails) {
        long id = userDetails.getId();
        userDetailsRepository.deleteById(id);
        return ResponseDomain.deleteResponse("Account updated successfully..");
    }


}

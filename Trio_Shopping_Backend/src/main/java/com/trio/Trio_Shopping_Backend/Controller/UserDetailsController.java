package com.trio.Trio_Shopping_Backend.Controller;

import com.trio.Trio_Shopping_Backend.domain.UserDetails;
import com.trio.Trio_Shopping_Backend.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserDetailsController {
    @Autowired
    UserDetailsService userDetailsService;
    @PostMapping("/createUser")
    ResponseEntity<?> creteUser(@RequestBody UserDetails userDetails)
    {
        return null;
    }
    @PutMapping("/updateUser")
    ResponseEntity<?> updateUserDetails(@RequestBody UserDetails userDetails)
    {
      return userDetailsService.updateUserDetails(userDetails);
    }
    @GetMapping("/getUserDetails{id}")
    Optional<UserDetails> getUserDetails(@PathVariable("id") long id){
        return userDetailsService.getUserDetails(id);
    }
    @DeleteMapping("/deleteAccount")
    ResponseEntity<?> deleteUserDetails(@RequestBody UserDetails userDetails)
    {
        return userDetailsService.deleteAccount(userDetails);
    }


}

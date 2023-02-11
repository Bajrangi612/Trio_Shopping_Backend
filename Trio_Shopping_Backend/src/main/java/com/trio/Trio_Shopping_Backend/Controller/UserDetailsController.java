package com.trio.Trio_Shopping_Backend.Controller;

import com.trio.Trio_Shopping_Backend.domain.OtpVO;
import com.trio.Trio_Shopping_Backend.domain.UserDetails;
import com.trio.Trio_Shopping_Backend.repository.UserDetailsRepository;
import com.trio.Trio_Shopping_Backend.responce.ResponseDomain;
import com.trio.Trio_Shopping_Backend.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/user")
@CrossOrigin("*")
public class UserDetailsController {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @PostMapping("/createUser/{mobileOrEmail}/{data}")
    ResponseEntity<?> creteUser(@PathVariable("mobileOrEmail") String mobileOrEmail, @PathVariable("data") String data) {
        UserDetails userDetails = new UserDetails();
        if(mobileOrEmail.equalsIgnoreCase("email")){
            UserDetails account = userDetailsRepository.findByUserEmail(data);
            if(account==null){
                 userDetails.setUserEmail(data);
                UserDetails  createdAccount =  userDetailsService.createUser(userDetails);
                return new ResponseEntity<>(createdAccount, HttpStatus.OK);
            }else {
                new ResponseEntity<>(account, HttpStatus.OK);
            }
        } else if (mobileOrEmail.equalsIgnoreCase("mobileNumber")) {
            UserDetails account = userDetailsRepository.findByUserMobileNumber(data);
            if(account==null){
                userDetails.setUserMobileNumber(data);
              UserDetails createdAccount =   userDetailsService.createUser(userDetails);
              return new ResponseEntity<>(createdAccount,HttpStatus.OK);
            }else {
                return new ResponseEntity<>(account,HttpStatus.OK);
            }

        }
        return ResponseDomain.badRequest();
    }

    @PutMapping("/updateUser")
    ResponseEntity<?> updateUserDetails(@RequestBody UserDetails userDetails) {
        return userDetailsService.updateUserDetails(userDetails);
    }

    @GetMapping("/getUserDetails{id}")
    Optional<UserDetails> getUserDetails(@PathVariable("id") long id) {
        return userDetailsService.getUserDetails(id);
    }

    @DeleteMapping("/deleteAccount")
    ResponseEntity<?> deleteUserDetails(@RequestBody UserDetails userDetails) {
        return userDetailsService.deleteAccount(userDetails);
    }


}

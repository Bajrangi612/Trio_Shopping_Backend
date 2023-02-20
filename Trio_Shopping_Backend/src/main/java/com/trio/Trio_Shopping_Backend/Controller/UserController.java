package com.trio.Trio_Shopping_Backend.Controller;

import com.trio.Trio_Shopping_Backend.domain.AuthRequest;
import com.trio.Trio_Shopping_Backend.domain.UserInfo;
import com.trio.Trio_Shopping_Backend.domain.UserInfoVO;
import com.trio.Trio_Shopping_Backend.dto.AccessTokenVO;
import com.trio.Trio_Shopping_Backend.repository.UserInfoRepository;
import com.trio.Trio_Shopping_Backend.service.EmailServiceImpl;
import com.trio.Trio_Shopping_Backend.service.JwtService;
import com.trio.Trio_Shopping_Backend.service.ProductService;
import com.trio.Trio_Shopping_Backend.service.UserInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private ProductService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private  static  final Logger log = LogManager.getLogger(EmailServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/authenticate")
    public AccessTokenVO authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        log.info("Entering  Controller ::: UserController ::: authenticateAndGetToken");
        AccessTokenVO accessTokenVO = new AccessTokenVO();
        Optional<UserInfo> userInfo = Optional.ofNullable((userInfoRepository.findByMobileNumber(authRequest.getUserName())));
        if (userInfo.isEmpty()) {
            log.info("Controller ::: UserController ::: authenticateAndGetToken ::: creating new account");
            UserInfo userInfo1 = new UserInfo();
            userInfo1.setMobileNumber(authRequest.getUserName());
            userInfo1.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            userInfo1.setRoles("ROLE_CUSTOMER");
            userInfoRepository.save(userInfo1);
            log.info("  Controller ::: UserController ::: authenticateAndGetToken ::: AccountCreated");
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            accessTokenVO.setToken(jwtService.generateToken(authRequest.getUserName()));
            log.info(" UserController ::: authenticateAndGetToken ::: token sent..");
            log.info("Exiting  Controller ::: UserController ::: authenticateAndGetToken  ");
            return accessTokenVO;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }

    @GetMapping("/getUserByMobileNumber")
    public UserInfoVO getUserByMobileNumber(Authentication authentication) {
        log.info("Entering  Controller ::: UserController ::: getUserByMobileNumber ::: ");
        return userInfoService.getUserDetails(authentication.getName());
    }

}

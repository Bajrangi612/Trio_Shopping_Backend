package com.trio.Trio_Shopping_Backend.Controller;

import com.trio.Trio_Shopping_Backend.repository.CustomerRepository;
import com.trio.Trio_Shopping_Backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/email")
public class SmsController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private CustomerRepository customerRepository;

    // Sending a simple Email
    @PostMapping("/sendMail")
    public ResponseEntity<?> sendMail(@RequestBody String data) {
        return emailService.sendOtpOnMail(data);
    }

    @PostMapping("/sendOTP/{mobileOrEmail}/{data}")
    public ResponseEntity<?> sendOTP(@PathVariable("mobileOrEmail") String mobileOrEmail, @PathVariable("data") String data) {
        if (mobileOrEmail.equalsIgnoreCase("email")) {
            return emailService.sendOtpOnMail(data);
        } else if (mobileOrEmail.equalsIgnoreCase("mobileNumber")) {
            return emailService.sendOtpOnNumber(data);
        }
        return null;
    }


}

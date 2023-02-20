package com.trio.Trio_Shopping_Backend.Controller;

import com.trio.Trio_Shopping_Backend.repository.CustomerRepository;
import com.trio.Trio_Shopping_Backend.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/email")
public class SmsController {
    private  static  final Logger log = LogManager.getLogger(SmsController.class);
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
        log.info("Entering to SMS Controller class");
        if (mobileOrEmail.equalsIgnoreCase("email")) {
            return emailService.sendOtpOnMail(data);
        } else if (mobileOrEmail.equalsIgnoreCase("mobileNumber")) {
            return emailService.sendOtpOnNumber(data);
        }
        log.info("Exiting to SMS Controller class");
        return null;
    }


}

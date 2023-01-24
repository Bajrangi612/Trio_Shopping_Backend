package com.trio.Trio_Shopping_Backend.Controller;

import com.trio.Trio_Shopping_Backend.domain.EmailDetails;
import com.trio.Trio_Shopping_Backend.domain.OtpVO;
import com.trio.Trio_Shopping_Backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RestController
@RequestMapping("/v1/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    // Sending a simple Email
    @PostMapping("/sendMail")
    public ResponseEntity<?> sendMail(@RequestBody OtpVO otpVO)
    {
        return emailService.sendSimpleMail(otpVO);
    }
}

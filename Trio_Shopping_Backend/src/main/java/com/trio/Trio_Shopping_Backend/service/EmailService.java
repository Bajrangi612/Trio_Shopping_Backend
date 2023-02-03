package com.trio.Trio_Shopping_Backend.service;

import com.trio.Trio_Shopping_Backend.domain.OtpVO;
import org.springframework.http.ResponseEntity;

public interface EmailService {
    ResponseEntity<?> sendOtpOnMail(String data);
    ResponseEntity<?> sendOtpOnNumber(String mobileNumber);

    // Method
    // To send an email with attachment
//    String sendMailWithAttachment(EmailDetails details);
}

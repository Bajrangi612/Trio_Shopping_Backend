package com.trio.Trio_Shopping_Backend.service;

import com.trio.Trio_Shopping_Backend.domain.EmailDetails;
import com.trio.Trio_Shopping_Backend.domain.OtpVO;
import org.springframework.http.ResponseEntity;

public interface EmailService {
    ResponseEntity<?> sendSimpleMail(OtpVO otpVO);

    // Method
    // To send an email with attachment
//    String sendMailWithAttachment(EmailDetails details);
}

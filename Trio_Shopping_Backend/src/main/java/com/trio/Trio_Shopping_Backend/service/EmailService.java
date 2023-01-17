package com.trio.Trio_Shopping_Backend.service;

import com.trio.Trio_Shopping_Backend.domain.EmailDetails;
import com.trio.Trio_Shopping_Backend.domain.OtpVO;

public interface EmailService {
    OtpVO sendSimpleMail(String mailId);

    // Method
    // To send an email with attachment
//    String sendMailWithAttachment(EmailDetails details);
}

package com.trio.Trio_Shopping_Backend.service;

import com.trio.Trio_Shopping_Backend.domain.EmailDetails;

public interface EmailService {
    boolean sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
//    String sendMailWithAttachment(EmailDetails details);
}

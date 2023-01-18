package com.trio.Trio_Shopping_Backend.service;

import com.trio.Trio_Shopping_Backend.domain.EmailDetails;

import com.trio.Trio_Shopping_Backend.domain.OtpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    // Method 1
    // To send a simple email
    @Override
    public ResponseEntity<?> sendSimpleMail(String mailId)
    {
        OtpVO otpVO = new OtpVO();
        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();


            // Generation OTP
            int sentOTP = new Random().nextInt(900000) + 100000;
            otpVO.setSentOTP(sentOTP);

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(mailId);
            mailMessage.setText("Hii dear, your OTP is "+sentOTP);
            mailMessage.setSubject("Trio-Shopping Verification Code");

            // Sending the mail
            javaMailSender.send(mailMessage);
            System.out.println("Mail Sent Successfully...");
            otpVO.setMailSent(true);
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            System.out.println("Error while Sending Mail");
            otpVO.setMailSent(false);
        }
        return  new ResponseEntity<>(otpVO, HttpStatus.OK);
    }
//    @Override
//    public String sendMailWithAttachment(EmailDetails details)
//    {
//        // Creating a mime message
//        MimeMessage mimeMessage
//                = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper;
//
//        try {
//
//            // Setting multipart as true for attachments to
//            // be send
//            mimeMessageHelper
//                    = new MimeMessageHelper(mimeMessage, true);
//            mimeMessageHelper.setFrom(sender);
//            mimeMessageHelper.setTo(details.getRecipient());
//            mimeMessageHelper.setText(details.getMsgBody());
//            mimeMessageHelper.setSubject(
//                    details.getSubject());
//
//            // Adding the attachment
//            FileSystemResource file
//                    = new FileSystemResource(
//                    new File(details.getAttachment()));
//
//            mimeMessageHelper.addAttachment(
//                    file.getFilename(), file);
//
//            // Sending the mail
//            javaMailSender.send(mimeMessage);
//            return "Mail sent Successfully";
//        }
//
//        // Catch block to handle MessagingException
//        catch (MessagingException e) {
//
//            // Display message when exception occurred
//            return "Error while sending mail!!!";
//        }
//    }
}

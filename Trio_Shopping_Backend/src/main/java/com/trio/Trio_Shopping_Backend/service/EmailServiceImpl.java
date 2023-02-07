package com.trio.Trio_Shopping_Backend.service;

import com.trio.Trio_Shopping_Backend.domain.EmailDetails;

import com.trio.Trio_Shopping_Backend.domain.OtpVO;
import com.trio.Trio_Shopping_Backend.responce.ResponseDomain;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    // Method 1
    // To send a simple email
    @Override
    public ResponseEntity<?> sendOtpOnMail(String data) {
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
            mailMessage.setTo(data);
            mailMessage.setText("Hii, " + sentOTP+" is the verification code to login in Trio_Shopping...");
            mailMessage.setSubject("Trio-Shopping Verification Code");

            // Sending the mail
            javaMailSender.send(mailMessage);
            otpVO.setMessage("Mail Sent Successfully...");
            otpVO.setOtpSend(true);
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            otpVO.setMessage("Error while Sending Mail");
            otpVO.setOtpSend(false);
        }
        return new ResponseEntity<>(otpVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> sendOtpOnNumber(String mobileNumber) {
        OtpVO otpVO = new OtpVO();
        StringBuffer response = null;
        try {
            String authorization = "ezv62jloC4TIqGtJ5VE8Kx9OpBZ0hNdQRagrfbHi1SUw3cPuDXJQd3Nr6FfxYZTmb1lyVoDKPhG9upgR";
            String route = "v3";
            String sender_id = "TXTIND";
            int sentOTP = new Random().nextInt(900000) + 100000;
            otpVO.setSentOTP(sentOTP);
            String message = "\t"+sentOTP+" is the verification code to login in Trio_Shopping...";
            URLEncoder.encode(message, "UTF-8");
            String numbers = mobileNumber;
            String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization="+authorization+"&sender_id="+sender_id+"&message="+message+"&route="+route+"&numbers="+numbers;
            URL url = new URL(myUrl);
            URLConnection connection = url.openConnection();
            HttpURLConnection conn = (HttpURLConnection) connection;
            conn.setRequestMethod("GET");
            conn.setRequestProperty("cache-control","no-cache");
            int responseCode = conn.getResponseCode();
            response = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while (true){
                String line = br.readLine();
                if(line==null)
                    break;
                response.append(line);
            }

            JSONObject jsonObject = new JSONObject(response.toString());
            if(jsonObject.getBoolean("return"))
           otpVO.setMessage(jsonObject.getJSONArray("message").get(0).toString());
            else otpVO.setMessage(jsonObject.getString("message"));
           otpVO.setOtpSend(jsonObject.getBoolean("return"));
           otpVO.setEmailIdOrMobileNumber(mobileNumber);
           if(!jsonObject.getBoolean("return")){
              return ResponseDomain.badRequest(otpVO.getMessage()+"-"+otpVO.isOtpSend());
           }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            otpVO.setMessage("OTP Sending Fail");
            otpVO.setOtpSend(false);
            return ResponseDomain.badRequest(otpVO.getMessage()+"-"+otpVO.isOtpSend());
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

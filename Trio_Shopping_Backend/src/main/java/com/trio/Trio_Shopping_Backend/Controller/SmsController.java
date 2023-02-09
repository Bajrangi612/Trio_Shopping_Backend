package com.trio.Trio_Shopping_Backend.Controller;

import com.trio.Trio_Shopping_Backend.domain.OtpVO;
import com.trio.Trio_Shopping_Backend.domain.UserDetails;
import com.trio.Trio_Shopping_Backend.repository.UserDetailsRepository;
import com.trio.Trio_Shopping_Backend.responce.ResponseDomain;
import com.trio.Trio_Shopping_Backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/email")
public class SmsController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    // Sending a simple Email
    @PostMapping("/sendMail")
    public ResponseEntity<?> sendMail(@RequestBody String data) {
        return emailService.sendOtpOnMail(data);
    }

    @PostMapping("/sendOTP/{mobileOrEmail}/{data}")
    public ResponseEntity<?> sendOTP(@PathVariable("mobileOrEmail") String mobileOrEmail, @PathVariable("data") String data) {
        if (mobileOrEmail.equalsIgnoreCase("email")) {
            UserDetails userDetails = userDetailsRepository.findByUserEmail(data);
            if (userDetails == null) {
                OtpVO otpVO = new OtpVO();
                otpVO.setOtpSend(false);
                otpVO.setEmailIdOrMobileNumber(data);
                otpVO.setAccountRegistered(false);
                return new ResponseEntity<>(otpVO, HttpStatus.OK);
            }else{
                return emailService.sendOtpOnMail(data);
            }
        }else if (mobileOrEmail.equalsIgnoreCase("mobileNumber")){
            UserDetails userDetails = userDetailsRepository.findByUserMobileNumber(data);
            if (userDetails == null) {
                OtpVO otpVO = new OtpVO();
                otpVO.setOtpSend(false);
                otpVO.setEmailIdOrMobileNumber(data);
                otpVO.setAccountRegistered(false);
                return new ResponseEntity<>(otpVO, HttpStatus.OK);
            }else {
                return emailService.sendOtpOnNumber(data);
            }
        }
//              You are not registered with us. Please sign up.


            return null;
        }


}

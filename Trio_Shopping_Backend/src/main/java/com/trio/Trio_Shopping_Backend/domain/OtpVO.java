package com.trio.Trio_Shopping_Backend.domain;

import lombok.Data;

@Data
public class OtpVO {
   private int sentOTP;
    private boolean isOtpSend;
    private String emailIdOrMobileNumber;
    private String message;
    private boolean isAccountRegistered = true;


}

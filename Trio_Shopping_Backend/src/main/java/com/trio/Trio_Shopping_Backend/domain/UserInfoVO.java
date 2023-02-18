package com.trio.Trio_Shopping_Backend.domain;

import lombok.Data;

@Data
public class UserInfoVO {
    private int id;
    private String UserName;
    private String emailId;
    private String mobileNumber;
    private String gender;
}

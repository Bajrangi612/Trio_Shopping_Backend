package com.trio.Trio_Shopping_Backend.domain;

import lombok.Data;

@Data
public class AuthRequest {
    private String userName;
    private String password;
}

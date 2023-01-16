package com.trio.Trio_Shopping_Backend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginUser {
    @RequestMapping("/")
    @ResponseBody
    public String Blank() {
        return "WELCOME TO TRIOSHOPPING";
    }

    @RequestMapping("/Ankit")
    @ResponseBody
    public String Demo() {
        return "Ankit";
    }
}

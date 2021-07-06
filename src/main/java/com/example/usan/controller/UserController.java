package com.example.usan.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/joinForm")
    public String joinUser() {
        return "user/joinForm";
    }
}

package com.example.usan.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/auth/joinForm")
    public String joinUser() { return "user/joinForm"; }

    @GetMapping("/auth/loginForm")
    public String loginForm() { return "user/loginForm"; }
}

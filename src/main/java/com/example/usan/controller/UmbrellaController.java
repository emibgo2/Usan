package com.example.usan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UmbrellaController {

    @GetMapping("/umb/joinForm")
    public String joinUmbrella() {
        return "umbrella/umb_joinForm";
    }
}

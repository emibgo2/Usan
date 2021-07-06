package com.example.usan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardCotroller {
    @GetMapping({"", "/"})
    public String index(){
        return "index";
    }
}

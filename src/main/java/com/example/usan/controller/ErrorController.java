package com.example.usan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorController {
    @RequestMapping("/error-page/500")
    public String error500(HttpServletRequest request, HttpServletResponse response) {

        return "thymeleaf/umbrella/error";
    }

}

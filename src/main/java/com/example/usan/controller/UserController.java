package com.example.usan.controller;


import com.example.usan.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping({"", "/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤러에서 세션 접근
        return "index";
    }

    @GetMapping("/auth/joinForm")
    public String joinUser() { return "user/joinForm"; }

    @GetMapping("/auth/loginForm")
    public String loginForm() { return "user/loginForm"; }
}

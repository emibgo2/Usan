package com.example.usan.controller;


import com.example.usan.config.auth.PrincipalDetail;
import com.example.usan.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private  StorageService storageService;

    @GetMapping({"", "/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤러에서 세션 접근
        return "thymeleaf/home";
    }

    @GetMapping("/chaerin")
    public String test (Model model) {
        model.addAttribute("storages", storageService.sto_upload());
        return "thymeleaf/chaerin/rent";
    }

    @GetMapping("/auth/joinForm")
    public String joinUser() { return "user/joinForm"; }

    @GetMapping("/auth/loginForm")
    public String loginForm() { return "thymeleaf/user/login"; }


}

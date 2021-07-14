package com.example.usan.controller;

import com.example.usan.config.auth.PrincipalDetail;
import com.example.usan.model.User;
import com.example.usan.repository.UserRepository;
import com.example.usan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardCotroller {



    @GetMapping({"", "/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤러에서 세션 접근

        return "index";
    }
}

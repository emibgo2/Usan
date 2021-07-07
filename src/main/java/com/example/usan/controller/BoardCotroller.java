package com.example.usan.controller;

import com.example.usan.model.User;
import com.example.usan.repository.UserRepository;
import com.example.usan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardCotroller {

    @Autowired
    UserService userService;

    @GetMapping({"", "/"})
    public String index(Model model) {

        return "index";
    }
}

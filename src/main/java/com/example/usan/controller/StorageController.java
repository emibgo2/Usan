package com.example.usan.controller;

import com.example.usan.config.auth.PrincipalDetail;
import com.example.usan.model.User;
import com.example.usan.repository.UmbrellaRepository;
import com.example.usan.repository.UserRepository;
import com.example.usan.service.StorageService;
import com.example.usan.service.UmbrellaService;
import com.example.usan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StorageController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private UmbrellaService umbrellaService;

    @GetMapping("/storage/joinForm")
    public String joinUmbrella() {
        return "storage/storage_joinForm";
    }
    @GetMapping("/storage/mappingForm")
    public String selectStorage(Model model) {
        model.addAttribute("storage", storageService.sto_upload());
        return "storage/storage_mappingForm";
    }

    @GetMapping("/storage/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("storage", storageService.sto_detail(id));
        model.addAttribute("umbrella", umbrellaService.umb_upload());
        return "storage/storage_detail";
    }

}

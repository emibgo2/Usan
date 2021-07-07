package com.example.usan.controller;

import com.example.usan.model.Umbrella;
import com.example.usan.service.UmbrellaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UmbrellaController {

    @Autowired
    private UmbrellaService umbrellaService;

    @GetMapping("/umb/joinForm")
    public String joinUmbrella() {
        return "umbrella/umb_joinForm";
    }

    @GetMapping("/umb/mappingFrom")
    public String mappingUmbrella(Model model) {
        model.addAttribute("umbrella",umbrellaService.umb_upload());
        return "umbrella/umb_mappingForm";
    }
}

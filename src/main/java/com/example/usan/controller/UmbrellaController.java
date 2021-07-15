package com.example.usan.controller;

import com.example.usan.model.Umbrella;
import com.example.usan.service.StorageService;
import com.example.usan.service.UmbrellaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UmbrellaController {

    @Autowired
    private UmbrellaService umbrellaService;

    @Autowired
    private StorageService storageService;

    @GetMapping("/umb/joinForm")
    public String joinUmbrella(Model model) {
        model.addAttribute("storage", storageService.sto_upload());
        return "umbrella/umb_joinForm";
    }

    @GetMapping("/umb/mappingForm")
    public String mappingUmbrella(Model model) {
        model.addAttribute("umbrella",umbrellaService.umb_upload());
        model.addAttribute("storage", storageService.sto_upload());
        return "umbrella/umb_mappingForm";
    }
    @GetMapping("/umb/returnForm")
    public String returnUmbrella(Model model) {
        model.addAttribute("umbrella",umbrellaService.umb_upload());
        return "umbrella/umb_returnForm";
    }
}

package com.example.usan.controller;

import com.example.usan.model.Umbrella;
import com.example.usan.service.StorageService;
import com.example.usan.service.UmbrellaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UmbrellaController {

    @Autowired
    private UmbrellaService umbrellaService;

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String getUmbList() {
        return "umbrella/index2";
    }


}

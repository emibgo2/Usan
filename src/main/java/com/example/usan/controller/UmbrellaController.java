package com.example.usan.controller;

import com.example.usan.model.Storage;
import com.example.usan.model.Umbrella;
import com.example.usan.service.StorageService;
import com.example.usan.service.UmbrellaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UmbrellaController {

    @Autowired
    private UmbrellaService umbrellaService;

    @Autowired
    private StorageService storageService;

    @GetMapping(value = "/home")
    public static String getUmbList(Model model) {
        return "umbrella/index2";
    }

    // Test를 위해 String Boot 내에서 View 까지 담당하는 코드 API 코드 따로 있음
    @GetMapping("/umb/joinForm")
    public String joinUmbrella(Model model) {
        List<Storage> storages = storageService.sto_upload();
        for (int i = 0; i < storages.size(); i++) {
            storages.get(i).setUmbrellaList(null);
        }
        model.addAttribute("storage", storages);

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
        List<Umbrella>umbrellas= umbrellaService.umb_upload();
        for (int i = 0; i < umbrellas.size(); i++) {
            umbrellas.get(i).setStorage(null);
            umbrellas.get(i).setOver_date(umbrellaService.get_Late_Date(i+1));
        }
        model.addAttribute("umbrella",umbrellas);
        return "umbrella/umb_returnForm";
    }
}




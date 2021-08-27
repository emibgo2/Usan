package com.example.usan.controller;

import com.example.usan.model.Storage;
import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.repository.UserRepository;
import com.example.usan.service.StorageService;
import com.example.usan.service.UmbrellaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/umb")
@AllArgsConstructor
public class UmbrellaController {

    private UmbrellaService umbrellaService;
    private StorageService storageService;
    private UserRepository userRepository;


    @GetMapping(value = "/home")
    public static String getUmbList(Model model) {
        return "umbrella/index2";
    }


    // Test를 위해 String Boot 내에서 View 까지 담당하는 코드 API 코드 따로 있음
    @GetMapping("/joinForm/admin")
    public String joinUmbrella(Model model) {
        List<Storage> storages = storageService.sto_upload();
        for (int i = 0; i < storages.size(); i++) {
            storages.get(i).setUmbrellaList(null);
        }
        model.addAttribute("storage", storages);

        return "umbrella/umb_joinForm";
    }

    @GetMapping("/mappingForm")
    public String mappingUmbrella(Model model) {
        model.addAttribute("umbrella", umbrellaService.umb_upload());
        model.addAttribute("storage", storageService.sto_upload());
        return "umbrella/umb_mappingForm";
    }

    @GetMapping("/returnForm/{userId}")
    public String returnUmbrella(@PathVariable int userId, Model model) {
        List<Umbrella> umbrellas = getUserUmbrellas(userId);
        model.addAttribute("umbrella", umbrellas);
        return "umbrella/umb_returnForm";
    }

    private List<Umbrella> getUserUmbrellas(int userId) {
        User user = userRepository.findById(userId).orElseGet(() -> {
            return new User();
        });
        Umbrella umbrella1 = umbrellaService.getUmbrella(user.getUmbrella_Id1());
        Umbrella umbrella2 = umbrellaService.getUmbrella(user.getUmbrella_Id2());

        List<Umbrella> umbrellas2 = new ArrayList<>();
        umbrellas2.add(umbrella1);
        umbrellas2.add(umbrella2);
        return umbrellas2;
    }

    @GetMapping("/fault/report/{userId}")
    public String fault_ReportUmbrella(@PathVariable int userId, Model model) {
        List<Umbrella> umbrellas = getUserUmbrellas( userId);
        model.addAttribute("umbrella", umbrellas);
        return "umbrella/umb_Fault_Report";
    }

    @GetMapping("/fault/list/admin")
    public String fault_ListUmbrella(Model model) {
        List<Umbrella> umbrellas = umbrellaService.umb_upload();
        for (int i = 0; i < umbrellas.size(); i++) {
            umbrellas.get(i).setStorage(null);
            umbrellas.get(i).setOver_date(umbrellaService.get_Late_Date(i + 1));
        }
        model.addAttribute("umbrella", umbrellas);
        return "umbrella/umb_Fault_List";
    }


}




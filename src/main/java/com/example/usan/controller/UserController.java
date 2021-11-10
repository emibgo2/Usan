package com.example.usan.controller;


import com.example.usan.config.auth.PrincipalDetail;
import com.example.usan.model.Umbrella;
import com.example.usan.repository.UserRepository;
import com.example.usan.service.StorageService;
import com.example.usan.service.UmbrellaService;
import com.example.usan.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {



    @Autowired
    private StorageService storageService;
    private UserService userService;
    private UserRepository userRepository;
    private UmbrellaService umbrellaService;

    @GetMapping({"", "/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤러에서 세션 접근
        return "thymeleaf/home";
    }

    @GetMapping("/test/control")
    public String testControl(Model model) {
        List<Umbrella> umbrellas = umbrellaService.umb_upload();
        for (int i = 0; i < umbrellas.size(); i++) {
            umbrellas.get(i).setStorage(null);
        }
        model.addAttribute("umb", umbrellas);
        model.addAttribute("user", userRepository.findAll());
        model.addAttribute("storage", storageService.sto_upload());

        return "thymeleaf/testControl";
    }
    @GetMapping("/test/control/edit")
    public String testControlEdit(Model model) {
        List<Umbrella> umbrellas = umbrellaService.umb_upload();
        for (int i = 0; i < umbrellas.size(); i++) {
            umbrellas.get(i).setStorage(null);
        }
        model.addAttribute("umb", umbrellas);
        model.addAttribute("user", userRepository.findAll());
        model.addAttribute("storage", storageService.sto_upload());

        return "thymeleaf/testControlEdit";
    }


    @GetMapping("/agree")
    public String agreeForm() {
        return "thymeleaf/umbrella/agree";
    }

    @PostMapping("/test")
    @ResponseBody
    public int connectionTest(@RequestBody String a) {
        System.out.println("requestValue = " + a);
        return HttpStatus.OK.value();

    }


    @GetMapping("/rent/finish")
    public String rentFinishForm() {
        return "thymeleaf/umbrella/rent_finish";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "thymeleaf/umbrella/Join_Form";
    }

    @GetMapping("/chaerin")
    public String test(Model model) {
        model.addAttribute("storages", storageService.sto_upload());
        return "thymeleaf/chaerin/rent";
    }

    @GetMapping("/auth/joinForm")
    public String joinUser() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "thymeleaf/user/login";
    }

    @GetMapping("/user/agree")
    public String agree() {
        return "thymeleaf/umbrella/agree";
    }


}
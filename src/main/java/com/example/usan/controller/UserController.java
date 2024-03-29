package com.example.usan.controller;


import com.example.usan.config.auth.PrincipalDetail;
import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.repository.StorageRepository;
import com.example.usan.repository.UmbrellaRepository;
import com.example.usan.repository.UserRepository;
import com.example.usan.service.StorageService;
import com.example.usan.service.UmbrellaService;
import com.example.usan.service.UserService;
import lombok.AllArgsConstructor;
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



    private StorageRepository storageRepository;
    private StorageService storageService;
    private UserService userService;
    private UmbrellaRepository umbrellaRepository;
    private UserRepository userRepository;
    private UmbrellaService umbrellaService;

    @GetMapping({"", "/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤러에서 세션 접근
        return "thymeleaf/home";
    }

    @GetMapping("/test/control")
    public String testControl(Model model) {
        List<Umbrella> umbrellas = umbrellaService.umb_upload();

        model.addAttribute("umb", umbrellas);
        model.addAttribute("user", userRepository.findAll());
        model.addAttribute("storage", storageService.sto_upload());

        return "thymeleaf/testControl";
    }
    @GetMapping("/test/control/edit")
    public String testControlEdit(Model model) {
        List<Umbrella> umbrellas = umbrellaService.umb_upload();

        model.addAttribute("umb", umbrellas);
        model.addAttribute("user", userRepository.findAll());
        model.addAttribute("storage", storageService.sto_upload());

        return "thymeleaf/testControlEdit";
    }

    @GetMapping("/user/bill")
    public String billForm(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        User user = userRepository.findById(principalDetail.getUser().getId()).orElseGet(() -> {
            return new User();
        });
        model.addAttribute("user",user);
        return "thymeleaf/umbrella/bill";
    }


    @GetMapping("/payment/edit")
    public String paymentEdit() {
        return "thymeleaf/umbrella/payment_edit";
    }

    @GetMapping("/agree")
    public String agreeForm() {
        return "thymeleaf/umbrella/agree";
    }



    @GetMapping("/rent/finish")
    public String rentFinishForm() {
        return "thymeleaf/umbrella/rent_finish";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "thymeleaf/umbrella/Join_Form";
    }



    @GetMapping("/auth/joinForm")
    public String joinUser() {
        return "thymeleaf/user/Join_Form";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "thymeleaf/user/login";
    }

    @GetMapping("/user/agree")
    public String agree() {
        return "thymeleaf/user/agree";
    }

    @GetMapping("/personal/information")
    public String personalInformationForm(Model model, @AuthenticationPrincipal PrincipalDetail principal) {
        model.addAttribute("user", principal);
        return "thymeleaf/user/personal_information";
    }
    //------------------- admin page
    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("umb",umbrellaService.umb_upload());
        model.addAttribute("storages",storageService.sto_upload());
        model.addAttribute("users",userRepository.findAll());
        return  "thymeleaf/chaerin/manager";
    }
}
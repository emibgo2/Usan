package com.example.usan.controller;


import com.example.usan.config.auth.PrincipalDetail;
import com.example.usan.dto.ResponseDto;
import com.example.usan.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private  StorageService storageService;

    @GetMapping({"", "/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤러에서 세션 접근
        return "thymeleaf/home";
    }

    @GetMapping("/agree")
    public String agreeForm() {
        return "thymeleaf/umbrella/agree";
    }

    @PostMapping("/test")
    @ResponseBody
    public ResponseDto connectionTest(String a) {
        System.out.println("requestValue = " + a);
        return new ResponseDto<String>(HttpStatus.OK.value(), "ok");

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
    public String test (Model model) {
        model.addAttribute("storages", storageService.sto_upload());
        return "thymeleaf/chaerin/rent";
    }

    @GetMapping("/auth/joinForm")
    public String joinUser() { return "user/joinForm"; }

    @GetMapping("/auth/loginForm")
    public String loginForm() { return "thymeleaf/user/login"; }

    @GetMapping("/user/agree")
    public String agree() { return "thymeleaf/umbrella/agree";}



}

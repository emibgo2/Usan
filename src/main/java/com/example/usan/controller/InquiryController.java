package com.example.usan.controller;


import com.example.usan.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;

@Controller
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    // 컨트롤러에서 세션을 어떻게 찾는지?
    @GetMapping({"/inquiry"})
    public String index(Model model ,@PageableDefault(size =3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable, HttpServletResponse response) {
        // /WEB-INF/views/joinForm.jsp

        model.addAttribute("inquirys", inquiryService.inquiryList(pageable));
        return "inquiry/inquiryList";
        //InquiryController는 REST Controller가 아닌 그냥 Controller이기 때문에
        // 리턴할때 viewResolver가 작동 위에 inquirys를 라는 이름으로 글목록()을 들고갑니다.
        //
    }
    @GetMapping({"/inquiry/admin"})
    public String index_ADMIN(Model model ,@PageableDefault(size =3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable, HttpServletResponse response) {
        // /WEB-INF/views/joinForm.jsp

        model.addAttribute("inquirys", inquiryService.inquiryList(pageable));
        return "inquiry/ADMIN_inquiryList";
        //InquiryController는 REST Controller가 아닌 그냥 Controller이기 때문에
        // 리턴할때 viewResolver가 작동 위에 inquirys를 라는 이름으로 글목록()을 들고갑니다.
        //
    }
    @GetMapping("/inquiry/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("inquiry", inquiryService.inquiryDetail(id));
        return "inquiry/detail";
    }

    @GetMapping("/inquiry/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model ){
        model.addAttribute("inquiry", inquiryService.inquiryDetail(id));
        return "inquiry/updateForm";
    }

    // USER 권한이 필요
    @GetMapping("/inquiry/saveForm")
    public String saveForm(){
        return "inquiry/saveForm";
    }


}



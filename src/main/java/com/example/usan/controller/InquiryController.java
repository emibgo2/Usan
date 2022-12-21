package com.example.usan.controller;


import com.example.usan.service.InquiryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {

    private InquiryService inquiryService;

    @GetMapping
    public String index(Model model ,@PageableDefault(size =3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable, HttpServletResponse response) {
        // /WEB-INF/views/joinForm.jsp
        model.addAttribute("inquirys", inquiryService.inquiryList(pageable));
        return "thymeleaf/inquiry/inquiry";
        //InquiryController는 REST Controller가 아닌 그냥 Controller이기 때문에
        // 리턴할때 viewResolver가 작동 위에 inquirys를 라는 이름으로 글목록()을 들고갑니다.
        //
    }

    @GetMapping({"/admin/answer"})
    public String index_ADMIN_Answer(Model model ,@PageableDefault(size =3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable, HttpServletResponse response) {

        model.addAttribute("inquirys", inquiryService.inquiryList(pageable));
        return "inquiry/ADMIN_inquiryList_Answer";

    }
    @GetMapping({"/admin/noanswer"})
    public String index_ADMIN_NoAnswer(Model model ,@PageableDefault(size =3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable, HttpServletResponse response) {
        model.addAttribute("inquirys", inquiryService.inquiryList(pageable));
        return "inquiry/ADMIN_inquiryList_NoAnswer";

    }
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("inquiry", inquiryService.inquiryDetail(id));
        return "inquiry/detail";
    }

    @GetMapping("/{id}/updateForm")
    public String updateForm(@PathVariable Long id, Model model ){
        model.addAttribute("inquiry", inquiryService.inquiryDetail(id));
        return "inquiry/updateForm";
    }

    // USER 권한이 필요
    @GetMapping("/saveForm")
    public String saveForm(){
        return "inquiry/saveForm";
    }


}



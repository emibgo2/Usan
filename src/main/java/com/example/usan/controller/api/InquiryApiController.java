package com.example.usan.controller.api;


import com.example.usan.config.auth.PrincipalDetail;
import com.example.usan.dto.LikeSaveRequestDto;
import com.example.usan.dto.ReplySaveRequestDto;
import com.example.usan.dto.ResponseDto;
import com.example.usan.model.Inquiry;
import com.example.usan.service.InquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;

@RestController
@RequestMapping("/api/inquiry")
public class InquiryApiController {

    @Autowired
    private InquiryService inquiryService;

    @PostMapping
    public ResponseDto<Integer> save(@RequestBody Inquiry inquiry, @AuthenticationPrincipal PrincipalDetail principal) {
        // 공지사항을 저장하는 메소드
        inquiryService.writeInquiry(inquiry, principal.getUser());
        // Inquiry의 내용과 작성 유저의 정보를 DB에 저장
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        inquiryService.inquiryDelete(id); // 해당 ID의 Inquiry를 DB에서 삭제
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }

    @PatchMapping("/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Inquiry inquiry, @RequestBody int userId) throws IOException, ServletException {
        System.out.println("ㅅㅂ 오긴오냐?");
        inquiryService.inquiryModify(id, inquiry,userId); // 해당 ID의 Inquiry를 내용을 수정하여 다시 저장
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    @PutMapping("/{id}/answer/{userId}")
    public ResponseDto<Integer> answer(@PathVariable int id, @RequestBody Inquiry inquiry,@PathVariable int userId) {
        System.out.println("???");
        inquiryService.inquiryAnswer(id, inquiry,userId); // 해당 ID의 Inquiry를 내용을 수정하여 다시 저장
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}

package com.example.usan.controller.api;

import com.example.usan.dto.ResponseDto;
import com.example.usan.model.User;
import com.example.usan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;



    @PostMapping("/auth/post/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        userService.joinMember(user,1);
        return new ResponseDto<Integer> (HttpStatus.OK.value(),1);
        // 유저 회원가입 메소드
    }
    // Role Type 1: User, Role Type 2:ADMIN
    @PostMapping("/auth/post/admin/joinProc")
    public ResponseDto<Integer> admin_Save(@RequestBody User user) {
        userService.joinMember(user,2);
        return new ResponseDto<Integer> (HttpStatus.OK.value(),1);
        // 관리자 회원가입 메소드
    }


//   기존 로그인 방식
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//        System.out.println("UserApiController: login 호출됨");
//        User principal = userService.login(user);
//        if (principal != null) {
//            session.setAttribute("principal",principal);
//        }
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }

}

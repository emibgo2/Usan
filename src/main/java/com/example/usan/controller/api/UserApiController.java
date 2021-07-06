package com.example.usan.controller.api;

import com.example.usan.dto.ResponseDto;
import com.example.usan.model.User;
import com.example.usan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("save 호출");
        userService.joinMember(user);
        return new ResponseDto<Integer> (HttpStatus.OK.value(),1);
    }
}

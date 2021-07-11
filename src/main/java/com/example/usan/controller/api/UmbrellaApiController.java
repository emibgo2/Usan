package com.example.usan.controller.api;

import com.example.usan.dto.ResponseDto;
import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.service.UmbrellaService;
import com.example.usan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UmbrellaApiController {

    @Autowired
    UserService userService;

    @Autowired
    UmbrellaService umbrellaService;

    @PostMapping("/umb/joinProc")
    public ResponseDto<Integer> umb_save(@RequestBody Umbrella umbrella) {
        System.out.println("UmbrellaApiController: save 호출");
        umbrellaService.umbrella_save(umbrella);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/umb/mapping/{id}")
    public ResponseDto<Integer> umb_rent( @RequestBody User user,@PathVariable int id) {


        return new ResponseDto<Integer>(HttpStatus.OK.value(), userService.mappingUmbrella(id,user));
    }
}

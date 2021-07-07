package com.example.usan.controller.api;

import com.example.usan.dto.ResponseDto;
import com.example.usan.model.Umbrella;
import com.example.usan.service.UmbrellaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UmbrellaApiController {

    @Autowired
    UmbrellaService umbrellaService;

    @PostMapping("/umb/joinProc")
    public ResponseDto<Integer> umb_save(@RequestBody Umbrella umbrella) {
        System.out.println("UmbrellaApiController: save 호출");
        umbrellaService.umbrella_save(umbrella);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}

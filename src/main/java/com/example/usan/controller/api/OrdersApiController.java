package com.example.usan.controller.api;

import com.example.usan.config.auth.PrincipalDetail;
import com.example.usan.dto.ResponseDto;
import com.example.usan.service.OrdersService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersApiController {

    private final OrdersService ordersService;

    @PostMapping
    public ResponseDto<Integer> ordersSave(@AuthenticationPrincipal PrincipalDetail principalDetail, String location) {
        ordersService.ordersSave(principalDetail.getUser(),location);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}

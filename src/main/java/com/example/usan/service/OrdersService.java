package com.example.usan.service;

import com.example.usan.model.User;
import com.example.usan.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository orderRepository;

    @Transactional
    public void ordersSave(User user, String location) {
        // 미완성
    }
}

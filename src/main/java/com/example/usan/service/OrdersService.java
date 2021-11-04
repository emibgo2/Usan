package com.example.usan.service;

import com.example.usan.model.Orders;
import com.example.usan.model.User;
import com.example.usan.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrdersService {
    private final OrderRepository orderRepository;

    @Transactional
    public void ordersSave(User user, String location) {
        orderRepository.save(new Orders(user, location));
    }
}

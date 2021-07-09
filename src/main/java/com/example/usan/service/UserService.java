package com.example.usan.service;

import com.example.usan.model.RoleType;
import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.repository.UmbrellaRepository;
import com.example.usan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UmbrellaRepository umbrellaRepository;

    @Transactional
    public void joinMember(User user) {
        user.setPassword(user.getPassword());
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User login(User user) {
        User principal =userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        System.out.println("login User: "+principal);
        return principal;
    }

    @Transactional
    public void mappingUmbrella( int id ,  User user) {
        Umbrella umbrella = umbrellaRepository.findById(id).orElseGet(() -> {
            return new Umbrella();
        });
        if (user.getFirstUmbrella() != null) {
            if (user.getSecondUmbrella() != null) {
                System.out.println("두개다 꽉찼습니다");
            } else user.setSecondUmbrella(umbrella);
        } else {
            user.setFirstUmbrella(umbrella);

        }

    }
}

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

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    public void mappingUmbrella( int id ,  User requestUser) {
        Umbrella umbrella = umbrellaRepository.findById(id).orElseGet(() -> {
            return new Umbrella();
        });
        User user= userRepository.findById(requestUser.getId()).orElseGet(() -> {
            return new User();
        });

        user.setUmbrella_Id1(umbrella.getId());
        umbrella.setRent_date(Timestamp.valueOf(LocalDateTime.now()));
        umbrella.setUser_id(requestUser.getId());
        System.out.println("umbrella Information : "+umbrella);
        System.out.println("User     Information : "+user);

    }
}

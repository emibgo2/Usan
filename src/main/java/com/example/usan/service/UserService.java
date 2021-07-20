package com.example.usan.service;

import com.example.usan.model.RoleType;
import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.repository.UmbrellaRepository;
import com.example.usan.repository.UserRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private UserRepository userRepository;

    @Autowired
    private UmbrellaRepository umbrellaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void joinMember(User user) {
        String rawPassword = user.getPassword(); // 원문
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }



    @Transactional
    public int mappingUmbrella( int id ,  User requestUser, int rentPeriod) {
        Umbrella umbrella = umbrellaRepository.findById(id).orElseGet(() -> {
            return new Umbrella();
        });
        User user= userRepository.findById(requestUser.getId()).orElseGet(() -> {
            return new User();
        });

        if (user.getUmbrella_Id1() == 0) {
            user.setUmbrella_Id1(umbrella.getId());
            umbrella.setRent_date(Timestamp.valueOf(LocalDateTime.now()));
            umbrella.setEnd_date(Timestamp.valueOf(LocalDateTime.now().plusDays(rentPeriod)));
            umbrella.setUser_id(requestUser.getId());
            umbrella.setUse_count(umbrella.getUse_count()+1);
        } else if (user.getUmbrella_Id1() != 0 && user.getUmbrella_Id2() == 0) {
            user.setUmbrella_Id2(umbrella.getId());
            umbrella.setRent_date(Timestamp.valueOf(LocalDateTime.now()));
            umbrella.setEnd_date(Timestamp.valueOf(LocalDateTime.now().plusDays(rentPeriod)));
            umbrella.setUser_id(requestUser.getId());
            umbrella.setUse_count(umbrella.getUse_count()+1);
        }
        else return 3;


        System.out.println("umbrella Information : "+umbrella);
        System.out.println("User     Information : "+user);
        return 1;
    }


    @Transactional
    public int returnUmbrella(int id, User requestUser) {
        Umbrella umbrella = umbrellaRepository.findById(id).orElseGet(() -> {
            return new Umbrella();
        });
        User user= userRepository.findById(requestUser.getId()).orElseGet(() -> {
            return new User();
        });
        if (user.getUmbrella_Id1() == umbrella.getId()) {
            user.setUmbrella_Id1(0);
            umbrella.setUser_id(0);
        } else if (user.getUmbrella_Id2() == umbrella.getId()) {
            user.setUmbrella_Id2(0);
            umbrella.setUser_id(0);
        }else return 3;

        return 1;
    }
}

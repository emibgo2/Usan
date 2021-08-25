package com.example.usan;

import com.example.usan.model.RoleType;
import com.example.usan.model.User;
import com.example.usan.repository.UserRepository;
import com.example.usan.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class UsanApplication {


    public static void main(String[] args) {

        SpringApplication.run(UsanApplication.class, args);

    }

}

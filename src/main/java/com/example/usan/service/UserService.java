package com.example.usan.service;

import com.example.usan.model.RoleType;
import com.example.usan.model.User;
import com.example.usan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Transactional
    public void joinMember(User user) {
        user.setPassword(user.getPassword());
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }
}

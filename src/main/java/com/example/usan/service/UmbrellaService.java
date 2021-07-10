package com.example.usan.service;

import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.repository.UmbrellaRepository;
import com.example.usan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UmbrellaService {
    @Autowired
    private UmbrellaRepository umbrellaRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void umbrella_save(Umbrella umbrella) {
        umbrella.setId(umbrella.getId());
        umbrellaRepository.save(umbrella);
    }

    @Transactional(readOnly = true)
    public List<Umbrella> umb_upload() {
        List<Umbrella> umbrellas = umbrellaRepository.findAll();

        return umbrellas;
    }

    @Transactional
    public void mappingUser(int id, User user) {



    }
}

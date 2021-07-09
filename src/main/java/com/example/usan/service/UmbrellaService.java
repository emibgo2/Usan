package com.example.usan.service;

import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.repository.UmbrellaRepository;
import com.example.usan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Umbrella umb_upload() {
        Umbrella umbrella = umbrellaRepository.findById(1).orElseGet(() -> {
            return new Umbrella();
        });

        return umbrella;
    }

    @Transactional
    public void mappingUser(int id, User user) {



    }
}

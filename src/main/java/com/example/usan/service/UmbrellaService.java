package com.example.usan.service;

import com.example.usan.model.Umbrella;
import com.example.usan.repository.UmbrellaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UmbrellaService {
    @Autowired
    private UmbrellaRepository umbrellaRepository;

    @Transactional
    public void umbrella_save(Umbrella umbrella) {
        umbrella.setId(umbrella.getId());
        umbrellaRepository.save(umbrella);
    }
}

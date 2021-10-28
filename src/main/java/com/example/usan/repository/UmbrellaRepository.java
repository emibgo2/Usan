package com.example.usan.repository;

import com.example.usan.model.Umbrella;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UmbrellaRepository extends JpaRepository<Umbrella, Long> {
    Optional<Umbrella> findById(int id);

    Optional<Umbrella> findByValueOfRFID(String valueOfRFID);


}

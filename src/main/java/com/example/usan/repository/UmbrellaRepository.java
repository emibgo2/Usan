package com.example.usan.repository;

import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UmbrellaRepository extends JpaRepository<Umbrella, Integer> {
    Optional<Umbrella> findById(String id);
}

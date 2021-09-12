package com.example.usan.repository;

import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UmbrellaRepository extends JpaRepository<Umbrella, Long> {
    Optional<Umbrella> findById(int id);


}

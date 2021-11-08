package com.example.usan.repository;

import com.example.usan.model.Storage;
import com.example.usan.model.Umbrella;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StorageRepository extends JpaRepository<Storage,Long> {
    Optional<Storage> findByUmbrellaList(Umbrella umbrella);


}

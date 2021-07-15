package com.example.usan.repository;

import com.example.usan.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface StorageRepository extends JpaRepository<Storage,Integer> {



}

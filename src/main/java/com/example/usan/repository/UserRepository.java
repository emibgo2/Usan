package com.example.usan.repository;

import com.example.usan.model.RoleType;
import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // SELECT * FROM user WHERE username = 1?;
    // findBy = SELECT * FROM user <- Optinal findBy/Username -> WHERE username
//    Optional<User> findByUsername(String username);
    //JPA Naming 전략
// SELECT * FROM user WHERE username= ? AND password = ?;
    User findByUsernameAndPassword(String username, String password);

}

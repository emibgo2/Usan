package com.example.usan.repository;

import com.example.usan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // SELECT * FROM user WHERE username = 1?;
    Optional<User> findByUsername(String username);
    Optional<User> findByFirstUmbrellaIdOrSecondUmbrellaId(Long umbrella_Id1,Long umbrella_Id2);
    Optional<User> findByPayNumberContains(String payNumber);


//    Optional<User> findByPayNumber(String payNumber);
    // SELECT * FROM user WHERE username = 1?;
    // findBy = SELECT * FROM user <- Optinal findBy/Username -> WHERE username
//    Optional<User> findByUsername(String username);
    //JPA Naming 전략
// SELECT * FROM user WHERE username= ? AND password = ?;
//    User findByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT * FROM user WHERE username= ?1 AND password = ?2", nativeQuery = true)
//    User login(String username, String password);
}

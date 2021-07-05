package com.example.usan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30, unique = true)
    private String username; // ID

    @Column(nullable = false, length = 30, unique = true)
    private String nickName;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false,length = 50)
    private String email;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp
    private Timestamp createDate;

}

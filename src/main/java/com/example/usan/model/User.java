package com.example.usan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    public User(String username, String nickName, String password, String email, String phoneNumber, RoleType role, Timestamp createDate) {
        this.username = username;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.createDate = createDate;
    }

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

    @Column(nullable = true)
    private int umbrella_Id1;

    @Column(nullable = true)
    private int umbrella_Id2;

    @CreationTimestamp
    private Timestamp createDate;



    public User(String username, String nickName, String password, String email, String phoneNumber, RoleType role) {
        this.username = username;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", umbrellas=" + umbrella_Id1 +
                ", createDate=" + createDate +
                '}';
    }
}

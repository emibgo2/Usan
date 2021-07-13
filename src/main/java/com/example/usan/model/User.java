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
    @ColumnDefault("-1")
    private int umbrella_Id1;

    @Column(nullable = true)
    @ColumnDefault("-1")
    private int umbrella_Id2;

    @CreationTimestamp
    private Timestamp createDate;

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

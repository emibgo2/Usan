package com.example.usan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    public User(String username, String name,String nickName, String password, String email, String phoneNumber, RoleType role, Timestamp createDate) {
        this.username = username;
        this.name = name;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.createDate = createDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String username; // ID

    @NotNull
    @Column(nullable = false,length = 10)
    private String name;

    @Column(nullable = false, length = 30, unique = true)
    private String nickName;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column
    private int cash;

    @Column
    private int payNumber;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Column(nullable = true)
    private Long umbrella_Id1;

    @Column(nullable = true)
    private Long umbrella_Id2;

    @CreationTimestamp
    private Timestamp createDate;
//
//    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE) // mappedBy가 적혀잇으면 연관관계의 주인이 아니다( FK가 아니다) , DB에 컬럼을 만들지 마세요
//    @JsonIgnoreProperties({"user"})
//    @OrderBy("id asc")
//    @Column(nullable = true)
//    private List<Order> orderList;

    // Order.class 관련
//    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE) // mappedBy가 적혀잇으면 연관관계의 주인이 아니다( FK가 아니다) , DB에 컬럼을 만들지 마세요
//    @JsonIgnoreProperties({"user"})
//    @OrderBy("id desc ")
//    private List<Order> orderList ;

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
                ", cash=" + cash +
                ", payNumber=" + payNumber +
                ", role=" + role +
                ", umbrella_Id1=" + umbrella_Id1 +
                ", umbrella_Id2=" + umbrella_Id2 +
                ", createDate=" + createDate +
                '}';
    }
}

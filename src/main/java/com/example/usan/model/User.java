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

    @Column(nullable = false, length = 30)
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
    private String payNumber;

    @Column
    private String birth;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Column(nullable = true)
    private Long firstUmbrellaId;

    @Column(nullable = true)
    private Long secondUmbrellaId;

    @CreationTimestamp
    private Timestamp createDate;

//     Order.class 관련

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE) // mappedBy가 적혀잇으면 연관관계의 주인이 아니다( FK가 아니다) , DB에 컬럼을 만들지 마세요
    @JsonIgnoreProperties({"user"})
    @OrderBy("id desc ")
    private List<Orders> orderList ;

    public User(String username, String nickName, String password, String email, String phoneNumber, RoleType role) {
        this.username = username;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }


    public void setPayNumber(int day, int payNumber) {
        this.payNumber = day+"/"+payNumber;
    }

    public int getPayNumber() {
        if (payNumber !=null){ String[] split = payNumber.split("/");
            return Integer.parseInt(split[1]);
        } else return 0;
    }
    public int getRentDay() {
        if (payNumber !=null){ String[] split = payNumber.split("/");
            return Integer.parseInt(split[0]);
        } else return 0;
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
                ", firstUmbrellaId=" + firstUmbrellaId +
                ", secondUmbrellaId=" + secondUmbrellaId +
                ", createDate=" + createDate +
                '}';
    }
}

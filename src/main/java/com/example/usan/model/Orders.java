package com.example.usan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Orders {
    public Orders(User user, int payment, int discount, int day, String location) {
        this.user = user;
        this.payment = payment;
        this.discount = discount;
        this.day = day;
        this.location = location;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )    // IDENTITY = MYsql을 사용하면 Auto_increment, Oracle이면, Sequnce를 사용한다는 뜻.
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private int payment;

    private int discount;
    private int day;

    private Timestamp rentDay;

    private String location ;


    @CreationTimestamp  //시간이 자동으로 입력
    private Timestamp rentDate;
}


package com.example.usan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Umbrella {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private Timestamp create_date; // 우산 등록일

    @UpdateTimestamp
    private Timestamp rent_date; // 대여한 날짜

    @UpdateTimestamp
    private Timestamp over_date; // 연체된 날짜

    @UpdateTimestamp
    private Timestamp end_date; // 반납한 날짜

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;




}

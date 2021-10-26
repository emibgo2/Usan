//package com.example.usan.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//
//
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Entity
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY )    // IDENTITY = MYsql을 사용하면 Auto_increment, Oracle이면, Sequnce를 사용한다는 뜻.
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user")
//    private User user;
//
//    @Column(nullable = false)
//    private Long storageNumber;
//
//    @CreationTimestamp  //시간이 자동으로 입력
//    private Timestamp rentDate;
//}
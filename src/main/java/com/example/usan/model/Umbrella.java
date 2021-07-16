package com.example.usan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private Timestamp rent_date; // 대여한 날짜

    private Timestamp over_date; // 연체된 날짜


    private Timestamp end_date; // 반납한 날짜

    @Column()
    private int user_id;

    @Column
    private int use_count;

    @ManyToOne
    @JoinColumn(name = "storageId")
    private Storage storage;

//    추후 작업
//    @Column
//    private int 우산일련번호;
//
//    @Column
//    private String 우산_RFID_코드_값;

    @Override
    public String toString() {

        return "Umbrella{" +
                "id=" + id +
                ", create_date=" + create_date +
                ", rent_date=" + rent_date +
                ", over_date=" + over_date +
                ", end_date=" + end_date +
                ", user=" + user_id +
                '}';
    }


}

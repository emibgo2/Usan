package com.example.usan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

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

    private int over_date; // 연체된 일 수

    private Timestamp rent_end_date; // 반납을 해야 하는 날짜

    private Timestamp return_date; // 반납한 날짜

    @Column
    private Boolean failure_status; // 고장 여부

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
                ", return_date=" + return_date +
                ", user=" + user_id +
                '}';
    }


}

package com.example.usan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

// ORM -> Java Object -> 테이블로 매핑해주는 기술
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int umb_count; // 잔여 우산

    @Column
    private int umb_breakdown_count;

    @OneToMany(mappedBy = "storage", fetch = FetchType.EAGER, cascade =CascadeType.REFRESH)
    private List<Umbrella> umbrellaList;

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", umb_count=" + umb_count +
                ", umb_breakdown_count=" + umb_breakdown_count +
                ", umbrellaList=" + umbrellaList +
                '}';
    }
//    보관소 위치 ( 추후 방식 회의 예정 )

//    @Column
//    private int storage_location;
}

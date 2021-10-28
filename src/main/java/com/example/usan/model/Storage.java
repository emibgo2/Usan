package com.example.usan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
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
    private Long id;

    @Column
    private String location ; // 지점, 위치 ex) 평택대점

    @Column
    private int umb_count; // 잔여 우산

    @Column
    private int umb_breakdown_count;

    @CreationTimestamp
    private Timestamp create_date; // 우산 등록일

    @OneToMany(mappedBy = "storage", fetch = FetchType.EAGER, cascade =CascadeType.REFRESH)
    @JsonIgnoreProperties({"storage"})
    private List<Umbrella> umbrellaList;



//    보관소 위치 ( 추후 방식 회의 예정 )

//    @Column
//    private int storage_location;
}

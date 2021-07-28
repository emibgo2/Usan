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

// ORM -> Java Object -> 테이블로 매핑해주는 기술 (JPA)
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 됨


    @ManyToOne(fetch = FetchType.EAGER) // Many = Many, User =One
    @JoinColumn(name = "userId")
    private User user; // 문의자 객체

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adminId")
    private User admin;

    private String category;

    @Column(length = 100)
    private String answerTitle;

    @Lob // 대용량 데이터
    private String answerContent; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 됨

    @CreationTimestamp
    private Timestamp createDate;

    private Timestamp answerDate;

    @Column
    private Boolean answer;

}


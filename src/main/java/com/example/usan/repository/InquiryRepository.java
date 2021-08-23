package com.example.usan.repository;


import com.example.usan.model.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


// JSP로 치면 DAO
// 자동으로 bean 들고이 된다.
// @Repository 생략 가능
public interface InquiryRepository extends JpaRepository<Inquiry, Integer> {

    List<Inquiry> findByAnswerIsFalse();
    List<Inquiry> findByAnswerIsTrue();
}

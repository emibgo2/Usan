package com.example.usan.service;


import com.example.usan.model.Inquiry;
import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.repository.InquiryRepository;
import com.example.usan.repository.UserRepository;
import lombok.val;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. loC, 메모리에 띄워줌
@Service
public class InquiryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InquiryRepository inquiryRepository;
    @Transactional
    public void writeInquiry(Inquiry Inquiry, User user) { // title, Content
        
        Inquiry.setUser(user);
        inquiryRepository.save(Inquiry);
        // Inquiry의 내용과 작성한 User의 정보를 DB에 저장
    }


    @Transactional(readOnly = true)
    public Page<Inquiry> inquiryList(Pageable pageable) {
        return inquiryRepository.findAll(pageable);
    }
    // DB내의 Inquiry들을 갖고와서 @PageableDefault 내에서 지정하는 size 만큼 짤라서 return

    @Transactional(readOnly = true)
    public Inquiry inquiryDetail(int id) {
        return inquiryRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
                });
        // 해당 id 값을 가진 Inquiry의 세부 내용을 return
    }

    @Transactional
    public void inquiryDelete(int id) {
        inquiryRepository.deleteById(id);
    }
    // 해당 id 값의 Inquiry를 DB에서 삭제

    @Transactional
    public void inquiryModify(int id, Inquiry requestInquiry, int userId) {
        Inquiry Inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
                });
        Inquiry.setTitle(requestInquiry.getTitle());
        Inquiry.setContent(requestInquiry.getContent());
        // 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 됨. DB Flush
        // 해당 id 값의 Inquiry를 새로운 Title, Content를 집어넣어 DB값을 수정
    }

    @Transactional
    public void inquiryAnswer(int id, Inquiry requestInquiry,int userId) {
        User user= userRepository.findById(userId).orElseGet(() -> {
            return new User();
        });
        System.out.println(user);
        Inquiry Inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
                });
        Inquiry.setAnswerTitle(requestInquiry.getTitle());
        Inquiry.setAnswerContent(requestInquiry.getContent());
        Inquiry.setAdmin(user);
        Inquiry.setAnswerDate(Timestamp.valueOf(LocalDateTime.now()));
        Inquiry.setAnswer(true);
        // 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 됨. DB Flush
        // 해당 id 값의 Inquiry를 새로운 Title, Content를 집어넣어 DB값을 수정
    }
}
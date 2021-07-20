package com.example.usan.domain;


import com.example.usan.repository.UmbrellaRepository;
import com.example.usan.service.UmbrellaService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {

    @InjectMocks // Book Service 객체가 만들어 질 때 BookServiceUnitTest파일에 @Mock로 등록된 모든애들을 주입 받는다.
    private UmbrellaService umbrellaService;

    @Mock
    private UmbrellaRepository umbrellaRepository;

}

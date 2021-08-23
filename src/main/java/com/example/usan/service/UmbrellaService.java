package com.example.usan.service;

import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.repository.UmbrellaRepository;
import com.example.usan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class UmbrellaService {

    @Autowired
    private UmbrellaRepository umbrellaRepository;

    @Transactional
    public void umbrella_save(Umbrella umbrella) {
        umbrellaRepository.save(umbrella);
        // Umbrella를 DB에 저장
    }

    @Transactional
    public void umbrella_Fault_Report(int umbrellaId) {
        Umbrella umbrella = getUmbrella(umbrellaId);
        umbrella.setFailure_status(true); // 고장

    }


    @Transactional(readOnly = true)
    public List<Umbrella> umb_upload() {
        List<Umbrella> umbrellas = umbrellaRepository.findAll();
        return umbrellas;
        // DB내의 모든 Umbrella들을 return
    }

    @Transactional(readOnly = true)
    public int get_Late_Date(int id ){
        Umbrella umbrella = getUmbrella(id);
        LocalDate a = umbrella.getRent_date().toLocalDateTime().toLocalDate();
        LocalDate b = umbrella.getRent_end_date().toLocalDateTime().toLocalDate();
        Period period = Period.between(b,a);
        return period.getDays();
        // id 값에 해당하는 Umbrella의 대여한 날짜 ( Rent Date ) 와 반납 예정 날짜( Rent End Date )를
        // 비교, 계산하여 남은 일 수를 return ( 양수면 이미 지남 )
    }

    public Umbrella getUmbrella(int id) {
        return umbrellaRepository.findById(id).orElseGet(() -> {
                return new Umbrella();
            });
    }

}

package com.example.usan.controller.api;

import com.example.usan.controller.UmbrellaController;
import com.example.usan.dto.ResponseDto;
import com.example.usan.model.Storage;
import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.repository.StorageRepository;
import com.example.usan.repository.UmbrellaRepository;
import com.example.usan.repository.UserRepository;
import com.example.usan.service.StorageService;
import com.example.usan.service.UmbrellaService;
import com.example.usan.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/storage")
public class StorageApiController {

    private StorageService storageService;
    private UserService userService;
    private UmbrellaService umbrellaService;
    private StorageRepository storageRepository;
    private UserRepository userRepository;
    private UmbrellaRepository umbrellaRepository;
    private static User rentUser;
    @GetMapping(value = "/list")
    public List<Storage> joinUmbrella() {
        List<Storage> storages = storageService.sto_upload();
        return storages;
        // DB 내의 모든 Storage를 추합하여 전송
    }

    @PostMapping("arduino/payNumber")
    public ResponseDto findPayNumber(@RequestBody Map<String,Integer> requestNumber) {
//        System.out.println("payNumber = " + requestNumber);
//        String payNumber = requestNumber.replaceAll("[^0-9]", "");
//        System.out.println("payNumber = " + payNumber.length());
//        if (payNumber.length() != 4) {
//
//        }
//        int findNumber = Integer.parseInt(payNumber);
//        System.out.println(payNumber);
//        문자열 버전
//        인트 버전
        Integer payNumber = requestNumber.get("payNumber");
        if (String.valueOf(payNumber).length() != 4) {
            return new ResponseDto(HttpStatus.BAD_REQUEST.value(), payNumber);
        }
        User user = userRepository.findByPayNumber(payNumber).orElseThrow(() -> {
            return new IllegalArgumentException("해당 PayNumber를 갖고잇는 유저가 없습니다");
        });
        log.info("Rent request User ={}", user);
        rentUser = user;

        return new ResponseDto(HttpStatus.OK.value(), 1);
    }
    @PostMapping("/arduino/rfid")
    public ResponseDto findUmbrella(@RequestBody String valueOfRFID) {

        if (valueOfRFID.length() != 8) {
            return new ResponseDto(HttpStatus.BAD_REQUEST.value(), valueOfRFID);

        }
        Umbrella umbrella = umbrellaRepository.findByValueOfRFID(valueOfRFID).orElseThrow(() -> {
            return new IllegalArgumentException("해당 RFID 값을 가진 우산을 찾을 수 없습니다.");
        });
        log.info("Rent Umbrella ={}", umbrella);
        System.out.println(rentUser);
        userService.mappingUmbrella(umbrella.getId(), rentUser, UmbrellaController.result);
        System.out.println("payNumber = " + valueOfRFID);

        return new ResponseDto(HttpStatus.OK.value(), 1);
    }
    @PostMapping("/joinProc")
    public ResponseDto<Integer> storage_save(@RequestBody Storage storage) {
        storageService.storage_save(storage);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        // DB에 Storage를 저장
    }

    @PostMapping("/{id}/umb/{umb_id}/mapping")
    public ResponseDto<Integer> storage_umb_mapping(@PathVariable Long id, @PathVariable Long umb_id) {
        storageService.umb_save(id, umb_id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        // DB에 id에 해당하는 Storage와 umb_id에 해당하는 Umbrella를 맵핑
    }

    @PostConstruct
    public void init() {
        String[] location ={"평택대","평택역"};
        for ( int i = 1; i<=2; i++) {
            Long id = new Long(i);
            Storage storageCheck = storageRepository.findById(id).orElseGet(() -> {
                return new Storage();
            });
            if (storageCheck.getCreate_date() == null) {
                Storage storage = new Storage();
                storage.setLocation(location[i-1]);
                storage.setCreate_date(Timestamp.valueOf(LocalDateTime.now()));
                if (i==1) storage.setUmb_count(4);
                storageService.storage_save(storage);
                log.info(" 기본 보관소 생성 ");
            }else log.info(" 이미 보관소가 존재합니다.");
        }



    }
}

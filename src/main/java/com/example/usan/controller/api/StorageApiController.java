package com.example.usan.controller.api;

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

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/storage")
public class StorageApiController {
    public static final int Arduino_RENT_KEYPAD_BADREQUEST=600;
    public static final int Arduino_RENT_KEYPAD_SERVERERROR=700;
    public static final int Arduino_RENT_RFID_BADREQUEST=800;
    public static final int Arduino_RENT_RFID_SERVERERROR = 900;
    public static final int Arduino_RETURN_RFID_BADREQUEST=1000;
    public static final int Arduino_RETURN_RFID_SERVERERROR=1100;



    private StorageService storageService;
    private UserService userService;
    private UmbrellaService umbrellaService;
    private StorageRepository storageRepository;
    private UserRepository userRepository;
    private UmbrellaRepository umbrellaRepository;
    private static User rentUser;
    private static Umbrella rentUmbrella;

    @GetMapping(value = "/list")
    public List<Storage> joinUmbrella() {
        List<Storage> storages = storageService.sto_upload();
        return storages;
        // DB 내의 모든 Storage를 추합하여 전송
    }

    @PostMapping("/arduino/rfid")
    public Integer readyUmbrella(@RequestBody String valueOfRFID) {
        if (valueOfRFID.length() != 8) {
            return Arduino_RENT_RFID_BADREQUEST;
        }
        // rfid가 이미 등록된 상태이면 재등록 못도록 막아야함 ( 만들 예정 )
        storageService.lendingUmbReady(valueOfRFID);
        return HttpStatus.OK.value();
    }

    @PostMapping("arduino/payNumber/{pointNumber}")
    public Integer rent(@PathVariable Long pointNumber, @RequestBody String payNumber) {

        if (payNumber.indexOf("/")!= -1){ log.info("payNumber in \"/\""); return Arduino_RENT_KEYPAD_BADREQUEST;}
        if (payNumber.length() != 4) { log.info("payNumber length != 4");return Arduino_RENT_KEYPAD_BADREQUEST; }

        User user = userRepository.findByPayNumberContains(payNumber).orElseThrow(() -> {
            return new IllegalArgumentException("해당 PayNumber를 갖고잇는 유저가 없습니다");
        });

        Storage storage = storageRepository.findById(pointNumber).orElseThrow(() -> {
            return new IllegalArgumentException("해당 Storage를 찾을 수 없습니다");
        });
        if (storage.getLendingUmbRFID().isEmpty()) return StorageApiController.Arduino_RENT_KEYPAD_SERVERERROR;


        userService.mappingUmbrella(storage.getLendingUmbRFID(), user, user.getRentDay(),pointNumber,storage);

        log.info("Rent request User ={}", user);
        return HttpStatus.OK.value();
    }

    @PostMapping("/arduino/rfid/return")
    public Integer returnUmbrella(@RequestBody String valueOfRFID) {

        if (valueOfRFID.length() != 8) {
            return Arduino_RETURN_RFID_BADREQUEST;
        }
        return storageService.returnUmbrella(valueOfRFID);
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
        String[] location = {"평택대", "평택역"};
        for (int i = 1; i <= 2; i++) {
            Long id = new Long(i);
            Storage storageCheck = storageRepository.findById(id).orElseGet(() -> {
                return new Storage();
            });
            if (storageCheck.getCreate_date() == null) {
                Storage storage = new Storage();
                storage.setLocation(location[i - 1]);
                storage.setCreate_date(Timestamp.valueOf(LocalDateTime.now()));
                if (i == 1) storage.setUmb_count(4);
                storageService.storage_save(storage);
                log.info(" 기본 보관소 생성 ");
            } else log.info(" 이미 보관소가 존재합니다.");
        }


    }
}

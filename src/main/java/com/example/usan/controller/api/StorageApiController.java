package com.example.usan.controller.api;

import com.example.usan.dto.ResponseDto;
import com.example.usan.model.Storage;
import com.example.usan.repository.StorageRepository;
import com.example.usan.service.StorageService;
import com.example.usan.service.UmbrellaService;
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

    private StorageService storageService;
    private UmbrellaService umbrellaService;
    private StorageRepository storageRepository;

    @GetMapping(value = "/list")
    public List<Storage> joinUmbrella() {
        List<Storage> storages = storageService.sto_upload();
        return storages;
        // DB 내의 모든 Storage를 추합하여 전송
    }

    @PostMapping("/joinProc")
    public ResponseDto<Integer> storage_save(@RequestBody Storage storage) {
        storageService.storage_save(storage);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        // DB에 Storage를 저장
    }

    @PostMapping("/{id}/umb/{umb_id}/mapping")
    public ResponseDto<Integer> storage_umb_mapping(@PathVariable int id, @PathVariable int umb_id) {
        storageService.umb_save(id, umb_id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        // DB에 id에 해당하는 Storage와 umb_id에 해당하는 Umbrella를 맵핑
    }

    @PostConstruct
    public void init() {
        String[] location ={"평택대","평택역"};
        for ( int i = 1; i<=2; i++) {
            Storage storageCheck = storageRepository.findById(i).orElseGet(() -> {
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

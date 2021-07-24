package com.example.usan.controller.api;

import com.example.usan.dto.ResponseDto;
import com.example.usan.model.Storage;
import com.example.usan.model.Umbrella;
import com.example.usan.service.StorageService;
import com.example.usan.service.UmbrellaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StorageApiController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private UmbrellaService umbrellaService;

    @GetMapping(value = "/storage/get/list")
    public List<Storage> joinUmbrella() {
        List<Storage> storages = storageService.sto_upload();
        return storages;
        // DB 내의 모든 Storage를 추합하여 전송
    }

    @PostMapping("/storage/post/joinProc")
    public ResponseDto<Integer> storage_save(@RequestBody Storage storage) {
        storageService.storage_save(storage);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        // DB에 Storage를 저장
    }
    @PostMapping("/storage/post/{id}/umb/{umb_id}/mapping")
    public ResponseDto<Integer> storage_umb_mapping(@PathVariable int id ,@PathVariable int umb_id) {
        storageService.umb_save(id,umb_id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        // DB에 id에 해당하는 Storage와 umb_id에 해당하는 Umbrella를 맵핑
    }
}

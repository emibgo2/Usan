package com.example.usan.controller.api;

import com.example.usan.dto.ResponseDto;
import com.example.usan.model.Storage;
import com.example.usan.model.Umbrella;
import com.example.usan.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageApiController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/storage/joinProc")
    public ResponseDto<Integer> storage_save(@RequestBody Storage storage) {
        System.out.println("storageApiController: save 호출");
        storageService.storage_save(storage);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    @PostMapping("/storage/{id}/umb/{umb_id}/mapping")
    public ResponseDto<Integer> storage_umb_mapping(@PathVariable int id ,@PathVariable int umb_id) {
        System.out.println("storageApiController: mapping 호출");
        System.out.println("aaaaaa :::"+ umb_id);
        storageService.umb_save(id,umb_id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}

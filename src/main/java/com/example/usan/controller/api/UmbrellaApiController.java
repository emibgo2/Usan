package com.example.usan.controller.api;

import com.example.usan.controller.UmbrellaController;
import com.example.usan.dto.ResponseDto;
import com.example.usan.dto.StorageDto;
import com.example.usan.model.Storage;
import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.service.StorageService;
import com.example.usan.service.UmbrellaService;
import com.example.usan.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UmbrellaApiController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserService userService;

    @Autowired
    private UmbrellaService umbrellaService;

    @GetMapping("/umb/get/list")
    public List<Umbrella> returnUmbrella() {
        List<Umbrella> umbrellas = umbrellaService.umb_upload();

        return umbrellas;
    }

    @GetMapping("/umb/get/lateDate/day/{umbrellaId}")
    public int getLateDate(@PathVariable int umbrellaId){
        int LateDate = umbrellaService.get_Late_Date(umbrellaId);
        return LateDate;
    }


    @GetMapping("/umb/get/umbrellaList/storageList")
    public StorageDto<Integer, List<Umbrella>, List<Storage>> mappingUmbrella(Model model) {
        List<Umbrella> umbrellas = umbrellaService.umb_upload();
        List<Storage> storages = storageService.sto_upload();
        for (int i = 0; i < umbrellas.size(); i++) {
            umbrellas.get(i).setStorage(null);
        }
        return new StorageDto<>(HttpStatus.OK.value(), 1, umbrellas, storages);
    }

    @RequestMapping(value = "/string", method = RequestMethod.GET)
    public List<Storage> getUmbList2(Model model) {
        List<Storage> storages = storageService.sto_upload();
        return storages;
    }

    @PostMapping("/umb/post/joinProc")
    public ResponseDto<Integer> umb_save(@RequestBody Umbrella umbrella) {
        System.out.println("UmbrellaApiController: save 호출");
        umbrellaService.umbrella_save(umbrella);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/umb/put/mapping/{id}/{rentPeriod}")
    public ResponseDto<Integer> umb_rent(@RequestBody User user, @PathVariable int id, @PathVariable int rentPeriod) {
        return new ResponseDto<Integer>(HttpStatus.OK.value(), userService.mappingUmbrella(id, user,rentPeriod));
    }

    @PutMapping("/umb/put/return/{id}")
    public ResponseDto<Integer> umb_return(@RequestBody User user, @PathVariable int id) {
        return new ResponseDto<Integer>(HttpStatus.OK.value(), userService.returnUmbrella(id, user));
    }
}



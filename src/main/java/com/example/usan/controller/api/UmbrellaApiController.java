package com.example.usan.controller.api;

import com.example.usan.dto.ResponseDto;
import com.example.usan.dto.StorageDto;
import com.example.usan.model.Storage;
import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.repository.UmbrellaRepository;
import com.example.usan.service.StorageService;
import com.example.usan.service.UmbrellaService;
import com.example.usan.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/umb")
public class UmbrellaApiController {

    private StorageService storageService;
    private UserService userService;
    private UmbrellaService umbrellaService;
    private UmbrellaRepository umbrellaRepository;
    public static List<Integer> myUUID = Collections.synchronizedList(new ArrayList<>());
//    @PostMapping("/rent/{location}/{days}") // 지금 대여하는 사람이 누구여야하는지를 알아야하는데 QR코드 배급후 대여시 QR코드 인식하는걸로 생각중
//    public ResponseDto<Integer> rent(@PathVariable String location, @PathVariable int days, @AuthenticationPrincipal PrincipalDetail principal) {
//        System.out.println("UmbrellaApiController.test");
//        System.out.println("location = " + location);
//        System.out.println("days = " + days);
//        Random random = new Random();
//        int i = random.nextInt(UmbrellaApiController.myUUID.size());
//        Integer remove = UmbrellaApiController.myUUID.remove(i);
//        User user = userService.userPayNumber(principal.getUser().getId(), remove);
//        principal.getUser().setPayNumber(remove);
//        return new ResponseDto<Integer>(HttpStatus.OK.value(),user.getPayNumber());
//    }

    @GetMapping("/list")
    public List<Umbrella> returnUmbrella() {
        List<Umbrella> umbrellas = umbrellaService.umb_upload();
        return umbrellas;
        // DB안에 있는 Umbrella를 추합하여 전송
    }

    @GetMapping("/lateDate/day/{umbrellaId}")
    public int getLateDate(@PathVariable Long umbrellaId){
        int LateDate = umbrellaService.get_Late_Date(umbrellaId);
        return LateDate;
        // 해당 umbrellaId에 속하는 Umbrella에 있는 Late Date 필드를 가져와 return
    }


    @GetMapping("/umbrellaList/storageList")
    public StorageDto<Integer, List<Umbrella>, List<Storage>> mappingUmbrella(Model model) {
        List<Umbrella> umbrellas = umbrellaService.umb_upload();
        List<Storage> storages = storageService.sto_upload();
        for (int i = 0; i < umbrellas.size(); i++) {
            umbrellas.get(i).setStorage(null);
        }
        return new StorageDto<>(HttpStatus.OK.value(), 1, umbrellas, storages);
        // DB내의 Storage와 Umbrella를 모두 추합하여 전송
        // Umbrella는 이미 보내졌기 때문에 Storages내의 모든 Umbrella 정보는 제거하여 전송 ( 속도 상향을 위해 )
    }

    @RequestMapping(value = "/string", method = RequestMethod.GET)
    public List<Storage> getUmbList2(Model model) {
        List<Storage> storages = storageService.sto_upload();
        return storages;
        // DB내의 모든 Storage를 return ( Storage안의 Umbrella도 return )
    }



    @PostMapping("/joinProc")
    public ResponseDto<Integer> umb_save(@RequestBody Umbrella umbrella) {
        umbrellaService.umbrella_save(umbrella);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        // Umbrella를 DB 내에 저장
    }



    @PutMapping("/{id}/fault")
    public ResponseDto<Integer> umb_faultReport(@PathVariable Long id) {
        umbrellaService.umbrella_Fault_Report(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        // 프론트에서 날아온 User와 umbrellaId (id), 대여 기간(rentPeriod)를 추합하여 DB에 저장
    }

    @PutMapping("/mapping/{id}/{rentPeriod}")
    public ResponseDto<Integer> umb_rent(@RequestBody User user, @PathVariable Long id, @PathVariable int rentPeriod) {
        return new ResponseDto<Integer>(HttpStatus.OK.value(), userService.mappingUmbrella(id, user,rentPeriod));
        // 프론트에서 날아온 User와 umbrellaId (id), 대여 기간(rentPeriod)를 추합하여 DB에 저장
    }

    @PutMapping("/return/{id}")
    public ResponseDto<Integer> umb_return(@RequestBody User user, @PathVariable Long id) {
        return new ResponseDto<Integer>(HttpStatus.OK.value(), userService.returnUmbrella(id, user));
        // 프론트에서 날아온 User와 id를 갖고 반납처리하는 메소드
    }




    @PostConstruct
    public void init() throws InterruptedException {
        for (int i = 1000; i < 10000; i++) {
            myUUID.add(i);
        }
        // 우산이 없을시 기본 우산 4개 생성
        for (int i = 1; i <= 4; i++) {
            Long l = new Long(i);
            Umbrella umbrellaCheck = umbrellaRepository.findById(l).orElseGet(() -> {
                return new Umbrella();
            });
            if (umbrellaCheck.getCreate_date() == null) {
                Umbrella createUmbrella = new Umbrella();
                createUmbrella.setStorage(storageService.sto_detail(1L));
                if (i == 1) {
                    createUmbrella.setValueOfRFID("acde0139");
                }else   createUmbrella.setValueOfRFID("acde013"+i);
                System.out.println("createUmbrella = " + createUmbrella);
                umbrellaService.umbrella_save(createUmbrella);
                log.info("기본 우산 생성");
            } else log.info(" 이미 {}번 우산이 있습니다.",i);

        }

    }

}

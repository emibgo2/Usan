package com.example.usan.service;

import com.example.usan.controller.api.StorageApiController;
import com.example.usan.model.Storage;
import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.repository.StorageRepository;
import com.example.usan.repository.UmbrellaRepository;
import com.example.usan.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StorageService {

    private StorageRepository storageRepository;
    private UmbrellaRepository umbrellaRepository;
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Storage> sto_upload(){
        List<Storage> storages = storageRepository.findAll();
        return storages;
        // DB내에 모든 Sotrage를 return
    }
    @Transactional(readOnly = true)
    public Storage sto_detail(Long id){
        System.out.println("StorageService.sto_detail");
        System.out.println("id = " + id);
        return storageRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("보관소를 찾을 수 없습니다.");
                });
        // 해당 id값에 해당하는 Storage를 Return
    }
    @Transactional
    public void storage_save(Storage storage) {
        storageRepository.save(storage);
        // Storage를 DB에 저장
    }

    @Transactional
    public List<Umbrella> umb_List(Long id) {
        Storage storage= storageRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("실패 : Id를 찾을 수 없습니다");
        });
        List<Umbrella> storage_umbrellas = storage.getUmbrellaList();
        return storage_umbrellas;
        // 해당 id값에 해당하는 Sotrage에서 지정되어 있는 Umbrella들을 return ( 해당 Storage내의 Umbrella )
    }

    @Transactional
    public void umb_save(Long id ,Long umbrellaId) {
        Storage storage= storageRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("실패 : Id를 찾을 수 없습니다");
        });
        Umbrella umbrella = umbrellaRepository.findById(umbrellaId).orElseThrow(()->{
            return new IllegalArgumentException("실패 : Id를 찾을 수 없습니다");
        });
        List<Umbrella> test = storage.getUmbrellaList();
        test.add(umbrella);
        storage.setUmbrellaList(test);
        storage.setUmb_count(test.size());
        umbrella.setStorage(storage);
        // 위에 id값에 해당하는 Sotrage와 umbrellaId에 해당하는 Umbrella를 가져와
        // 서로가 해당 하는 필드에 서로를 저장 ( 맵핑 )
    }

    @Transactional
    public void lendingUmbReady(String valueOfRFID) {
        Umbrella umbrella = umbrellaRepository.findByValueOfRFID(valueOfRFID).orElseThrow(() -> {
            return new IllegalArgumentException("해당 RFID 값을 가진 우산을 찾을 수 없습니다.");
        });
        umbrella.getStorage().setLendingUmbRFID(umbrella.getValueOfRFID());
//        Storage storage = storageRepository.findById(umbrella.getStorage().getId()).orElseThrow(() -> {
//            return new IllegalArgumentException("해당 Storage를 찾을 수 없습니다");
//        });
//        storage.setLendingUmbRFID(umbrella.getValueOfRFID());
        log.info("Rent Umbrella ={}", umbrella);

        System.out.println("valueOf RFID = " + valueOfRFID);
    }

    @Transactional
    public int returnUmbrella(String valueOfRFID) {
        try {
            Umbrella umbrella = umbrellaRepository.findByValueOfRFID(valueOfRFID).orElseThrow(() -> {
                return new IllegalArgumentException("해당 RFID 값을 가진 우산을 찾을 수 없습니다.");
            });
            if (umbrella.getRent_date() == null) {
                return StorageApiController.Arduino_RETURN_RFID_BADREQUEST;
            }

            User returnUser = userRepository.findByFirstUmbrellaIdOrSecondUmbrellaId(umbrella.getId(),umbrella.getId()).orElseThrow(() -> {
                return new IllegalArgumentException("해당 우산을 대여한 유저가 없습니다");
            });

            if (returnUser.getFirstUmbrellaId() == umbrella.getId() ) {
                returnUser.setFirstUmbrellaId(null);
            }
            if (returnUser.getSecondUmbrellaId() == umbrella.getId()) {
                returnUser.setSecondUmbrellaId(null);
            }

            umbrella.setRent_date(null);
            umbrella.setUser_id(null);
            umbrella.setRent_end_date(null);
            umbrella.setReturn_date(Timestamp.valueOf(LocalDateTime.now()));
            umbrella.setOver_date(0);
            umbrella.getStorage().setUmb_count(umbrella.getStorage().getUmb_count()+1);
            return HttpStatus.OK.value();
        } catch (Exception e) {
            return StorageApiController.Arduino_RETURN_RFID_SERVERERROR;
        }
    }

    @Transactional
    public void drop() {
        List<Storage> all = storageRepository.findAll();
        for (Storage storage : all) {
            storage.setLendingUmbRFID(null);
        }
    }
}

package com.example.usan.service;

import com.example.usan.controller.StorageController;
import com.example.usan.model.Storage;
import com.example.usan.model.Umbrella;
import com.example.usan.repository.StorageRepository;
import com.example.usan.repository.UmbrellaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private UmbrellaRepository umbrellaRepository;

    @Transactional(readOnly = true)
    public List<Storage> sto_upload(){
        List<Storage> storages = storageRepository.findAll();
        return storages;
        // DB내에 모든 Sotrage를 return
    }
    @Transactional(readOnly = true)
    public Storage sto_detail(Long id){

        return storageRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
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
}

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

    }
    @Transactional(readOnly = true)
    public Storage sto_detail(int id){
        return storageRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
                });

    }
    @Transactional
    public void storage_save(Storage storage) {
        storage.setId(storage.getId());
        storageRepository.save(storage);
    }

    @Transactional
    public List<Umbrella> umb_List(int id) {

        Storage storage= storageRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("실패 : Id를 찾을 수 없습니다");
        });
        List<Umbrella> storage_umbrellas = storage.getUmbrellaList();
        return storage_umbrellas;
    }

    @Transactional
    public void umb_save(int id ,int umbrellaId) {
        System.out.println("거의다 옴");
        Storage storage= storageRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("실패 : Id를 찾을 수 없습니다");
        });
        Umbrella umbrella = umbrellaRepository.findById(umbrellaId).orElseThrow(()->{
            return new IllegalArgumentException("실패 : Id를 찾을 수 없습니다");
        });
        List<Umbrella> test = storage.getUmbrellaList();
        test.add(umbrella);
        storage.setUmbrellaList(test);
        umbrella.setStorage(storage);
        System.out.println(storage);
        System.out.println(umbrella);
    }
}

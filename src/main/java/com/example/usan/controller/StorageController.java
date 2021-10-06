package com.example.usan.controller;

import com.example.usan.config.auth.PrincipalDetail;
import com.example.usan.model.RoleType;
import com.example.usan.model.Storage;
import com.example.usan.service.StorageService;
import com.example.usan.service.UmbrellaService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/storage")
@AllArgsConstructor
public class StorageController {


    private StorageService storageService;
    private UmbrellaService umbrellaService;

    @GetMapping("/joinForm/admin")
    public String joinUmbrella(@AuthenticationPrincipal PrincipalDetail principal) {

        if (RoleType.ADMIN == principal.getUser().getRole()) {
            return "storage/storage_joinForm";
        } else return "앙대요";
    }

    @GetMapping("/mappingForm/admin")
    public String selectStorage(Model model) {
        List<Storage> storages = storageService.sto_upload();
        for (int i = 0; i < storages.size(); i++) {
            storages.get(i).setUmbrellaList(null);
        }

        model.addAttribute("storage", storages);
        return "storage/storage_mappingForm";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("storage", storageService.sto_detail(id));
        model.addAttribute("umbrella", umbrellaService.umb_upload());
        return "storage/storage_detail";
    }


    @GetMapping("/admin/{id}")
    public String findById_ADMIN(@PathVariable Long id, Model model) {
        model.addAttribute("storage", storageService.sto_detail(id));
        model.addAttribute("umbrella", umbrellaService.umb_upload());
        return "storage/storage_detail_ADMIN";
    }

}

package com.example.usan.controller;

import com.example.usan.config.auth.PrincipalDetail;
import com.example.usan.controller.api.UmbrellaApiController;
import com.example.usan.dto.ResponseDto;
import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.repository.UserRepository;
import com.example.usan.service.StorageService;
import com.example.usan.service.UmbrellaService;
import com.example.usan.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Controller
@RequestMapping("/umb")
@AllArgsConstructor
public class UmbrellaController {

    private UmbrellaService umbrellaService;
    private UserService userService;
    private StorageService storageService;
    private UserRepository userRepository;


    @PostMapping("/rent/{location}/{days}") // 지금 대여하는 사람이 누구여야하는지를 알아야하는데 QR코드 배급후 대여시 QR코드 인식하는걸로 생각중
    @ResponseBody
    public ResponseDto<Integer> rent(@PathVariable String location, @PathVariable int days, @AuthenticationPrincipal PrincipalDetail principal) {
        log.info("Lending location ={}", location);

        Random random = new Random();
        int i = random.nextInt(UmbrellaApiController.myUUID.size());
        Integer remove = UmbrellaApiController.myUUID.remove(i);
        if (principal.getUser().getPayNumber() == 0) {
            User user = userService.userPayNumber(principal.getUser().getId(), remove, days, location);
            if (user.getUsername() == null) {
                return new ResponseDto<Integer>(HttpStatus.OK.value(), 2);
            }
            log.info("Rent User = {}, Rent days={}", user,days);
        }
        //        result = days;
        // DB안에 있는 Umbrella를 추합하여 전송
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }

    @GetMapping("/pay/complete")
    public String home() {
        return "thymeleaf/umbrella/payComplete";
    }

    @GetMapping("/rent")
    public String viewTest(Model model) {

        model.addAttribute("storages", storageService.sto_upload());
        return "thymeleaf/umbrella/rent";
    }

    @GetMapping("/returnForm/{userId}")
    public String returnUmbrella(@PathVariable Long userId, Model model) {
        List<Umbrella> umbrellas = getUserUmbrellas(userId);
        model.addAttribute("umbrella", umbrellas);
        return "umbrella/umb_returnForm";
    }

    private List<Umbrella> getUserUmbrellas(Long userId) {
        User user = userRepository.findById(userId).orElseGet(() -> {
            return new User();
        });
        List<Umbrella> umbrellas2 = new ArrayList<>();


        if (user.getFirstUmbrellaId() != null) {
            Umbrella umbrella1 = umbrellaService.getUmbrella(user.getFirstUmbrellaId());
            umbrellas2.add(umbrella1);
            if (user.getSecondUmbrellaId() != null) {
                Umbrella umbrella2 = umbrellaService.getUmbrella(user.getSecondUmbrellaId());
                umbrellas2.add(umbrella2);
            }

        }
        return umbrellas2;
    }

    @GetMapping("/fault/report/{userId}")
    public String fault_ReportUmbrella(@PathVariable Long userId, Model model) {
        List<Umbrella> umbrellas = getUserUmbrellas(userId);
        model.addAttribute("umbrella", umbrellas);
        return "umbrella/umb_Fault_Report";
    }

    @GetMapping("/fault/list/admin")
    public String fault_ListUmbrella(Model model) {
        List<Umbrella> umbrellas = umbrellaService.umb_upload();
        for (int i = 0; i < umbrellas.size(); i++) {
            umbrellas.get(i).setStorage(null);
            umbrellas.get(i).setOver_date(umbrellaService.get_Late_Date(i + 1L));
        }
        model.addAttribute("umbrella", umbrellas);
        return "umbrella/umb_Fault_List";
    }

    @GetMapping("/guide")
    public String informationForm() {
        return  "thymeleaf/umbrella/generic";
    }

    @GetMapping("/rent/fail")
    public String rent_fail() {
        return "thymeleaf/umbrella/rent_fail";
    }

    @GetMapping("/rent/success")
    public String rent_Finish(Model model, @AuthenticationPrincipal PrincipalDetail principal) {
        System.out.println("principal = " + principal.getUser());
        User user = userRepository.findById(principal.getUser().getId()).orElseGet(() -> {
            return new User();
        });
        model.addAttribute("payNumber", user.getPayNumber());
        return "thymeleaf/umbrella/rent_finish";
    }

    @GetMapping("/card")
    public String cardForm() {
        return  "thymeleaf/umbrella/card";
    }

    @GetMapping("/docu")
    public String docuForm() {
        return  "thymeleaf/umbrella/docu";
    }


}



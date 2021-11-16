package com.example.usan.controller;

import com.example.usan.config.auth.PrincipalDetail;
import com.example.usan.controller.api.UmbrellaApiController;
import com.example.usan.dto.ResponseDto;
import com.example.usan.model.Storage;
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



    public void test(HttpServletRequest request) {

        Random random = new Random();
        int i = random.nextInt(UmbrellaApiController.myUUID.size());
        Integer remove = UmbrellaApiController.myUUID.remove(i);

        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관
        session.setAttribute("payNumber", remove);
        System.out.println(remove);

    }

    @GetMapping("/pay/complete")
    public String home() {
        return "thymeleaf/umbrella/payComplete";
    }

    @GetMapping(value = "/home")
    public static String getUmbList(Model model) {
        return "umbrella/index2";
    }


    // Test를 위해 String Boot 내에서 View 까지 담당하는 코드 API 코드 따로 있음
    @GetMapping("/joinForm/admin")
    public String joinUmbrella(Model model) {
        List<Storage> storages = storageService.sto_upload();
        for (int i = 0; i < storages.size(); i++) {
            storages.get(i).setUmbrellaList(null);
        }
        model.addAttribute("storage", storages);

        return "umbrella/umb_joinForm";
    }

//     이전 버전 rent View
//    @GetMapping("/mappingForm")
//    public String mappingUmbrella(Model model) {
//        model.addAttribute("umbrella", umbrellaService.umb_upload());
//        model.addAttribute("storage", storageService.sto_upload());
//        return "umbrella/umb_mappingForm";
//    }

    /**
     * d테스트용 메소드 위에 GetMapping과 변경할 예정
     */

    @GetMapping("/rent")
    public String viewTest(Model model) {

        model.addAttribute("storages", storageService.sto_upload());
        return "thymeleaf/umbrella/rent";
    }


    @GetMapping("/returnForm/{userId}")
    public String returnUmbrella(@PathVariable Long userId, Model model) {
        System.out.println("userId = " + userId);
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
    @GetMapping("/guide")
    public String informationForm() {
        return  "thymeleaf/umbrella/information";
    }

}



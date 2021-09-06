package com.example.usan.controller.api;

import com.example.usan.dto.ResponseDto;
import com.example.usan.model.RoleType;
import com.example.usan.model.User;
import com.example.usan.repository.UserRepository;
import com.example.usan.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class UserApiController {

    private UserService userService;
    private UserRepository userRepository;

    @PostMapping("/id/check")
    public ResponseDto<Integer> idCheck(@RequestBody User user) {

        return new ResponseDto<Integer>(HttpStatus.OK.value(), userService.checkMemberId(user));
        // 유저 회원가입 메소드
    }

    @PostMapping("/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        return new ResponseDto<Integer>(HttpStatus.OK.value(), userService.joinMember(user, 1));
        // 유저 회원가입 메소드
    }

    // Role Type 1: User, Role Type 2:ADMIN
    @PostMapping("/admin/joinProc")
    public ResponseDto<Integer> admin_Save(@RequestBody User user) {
        return new ResponseDto<Integer>(HttpStatus.OK.value(), userService.joinMember(user, 2));

        // 관리자 회원가입 메소드
    }

    @PostConstruct
    public void init() {

        User adminCheck = userRepository.findByUsername("admin").orElseGet(() -> {
            return new User();
        });
        if (adminCheck.getUsername() == null) {
            User admin = new User("admin", "관리자", "1", "emibgo@naver.com", "01029293999", RoleType.ADMIN, Timestamp.valueOf(LocalDateTime.now()));
            admin.setCash(100000);
            userService.joinMember(admin, 2);
            log.info("관리자 아이디 생성");
        } else log.info("이미 관리자 아이디가 있습니다.");
        User userCheck = userRepository.findByUsername("user").orElseGet(() -> {
            return new User();
        });
        if (userCheck.getUsername() == null) {
            User user = new User("user", "사용자", "1", "emibgo@naver.com", "01029293999", RoleType.USER, Timestamp.valueOf(LocalDateTime.now()));
            user.setCash(100000);
            userService.joinMember(user, 1);
            log.info("사용자 아이디 생성");
        }else log.info("이미 사용자 아이디가 있습니다.");

        
    }


//   기존 로그인 방식
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//        System.out.println("UserApiController: login 호출됨");
//        User principal = userService.login(user);
//        if (principal != null) {
//            session.setAttribute("principal",principal);
//        }
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }

}

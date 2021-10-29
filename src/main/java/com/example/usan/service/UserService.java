package com.example.usan.service;

import com.example.usan.model.RoleType;
import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.repository.UmbrellaRepository;
import com.example.usan.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@Slf4j
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UmbrellaRepository umbrellaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public int checkMemberId(User requestUser) {
        User user= userRepository.findByUsername(requestUser.getUsername()).orElseGet(() -> {
            return new User();
        });
        System.out.println(user);
        if (user.getPassword() == null) {
            System.out.println("result: "+ 1);
            return 1;
            // 해당 아이디 사용가능
        }
        else{             System.out.println("result: "+ 2);
            return 2;}


    }

    @Transactional
    public User userPayNumber(Long userId, int payNumber) {
        try {
            User user = userRepository.findById(userId).orElseGet(() -> {
                return new User();
            });
            user.setPayNumber(payNumber);
            log.info("Pay Number 배급이 성공하였습니다.");
            return user;
        } catch (Exception e) {
            log.info("Pay Number 배급이 실패하였습니다");
        }
        return new User();
    }

    @Transactional
    public int joinMember(User user,int roleType) {
        int checkResult = checkMemberId(user);
        if (checkResult == 1) {
            String rawPassword = user.getPassword(); // 원문
            String encPassword = encoder.encode(rawPassword);
            user.setPassword(encPassword);
            if (roleType == 1) user.setRole(RoleType.USER);
            else if (roleType == 2) user.setRole(RoleType.ADMIN);
            try {
                userRepository.save(user);
            }catch (Exception e){
                return 3;
            }
            return 1;
            // User의 정보와 비밀번호를 해쉬한 값을 DB에 저장
        }else return 2;

    }

    @Transactional(readOnly = true)
    public User boardUser(Long id){
        return userRepository.findById(id)
                .orElseGet(() -> {
                    return new User("admin", "고지훈","관리자", "1", "emibgo@naver.com", "01029293999", RoleType.ADMIN, Timestamp.valueOf(LocalDateTime.now()));
                });
        // 해당 id값에 해당하는 Storage를 Return
    }

    @Transactional
    public int mappingUmbrella( Long id ,  User requestUser, int rentPeriod) {
        Umbrella umbrella = umbrellaRepository.findById(id).orElseGet(() -> {
            return new Umbrella();
        });
        log.info("BeforeUser -> "+requestUser);
        User user= userRepository.findById(requestUser.getId()).orElseGet(() -> {
            return new User();
        });
        System.out.println("user = " + user);
        if (user.getUmbrella_Id1() == null||user.getUmbrella_Id1() == 0  ) {
            user.setUmbrella_Id1(umbrella.getId());
            umbrella.setRent_date(Timestamp.valueOf(LocalDateTime.now())); // 빌린 당시의 날을 저장
            umbrella.setRent_end_date(Timestamp.valueOf(LocalDateTime.now().plusDays(rentPeriod)));
            // 반납 날짜 =빌린 당시의 날(Rent_date) + 사용자가 지정한 대여 일 수(rentPeriod)
            umbrella.setUser_id(requestUser.getId());
            umbrella.setUse_count(umbrella.getUse_count()+1);
        } else if ((user.getUmbrella_Id1() == null||user.getUmbrella_Id1() != 0)  && (user.getUmbrella_Id2() == null||user.getUmbrella_Id2() == 0 )) {
            user.setUmbrella_Id2(umbrella.getId());
            umbrella.setRent_date(Timestamp.valueOf(LocalDateTime.now()));
            umbrella.setRent_end_date(Timestamp.valueOf(LocalDateTime.now().plusDays(rentPeriod)));
            umbrella.setUser_id(requestUser.getId());
            umbrella.setUse_count(umbrella.getUse_count()+1);
        }
        // User의 Umbrella_Id 값이 0 이면 빌린 우산이 없다는 뜻
        // Umbrella_Id1값이 0이면 User의 Umbrella_Id1에 저장
        // Umbrella_Id1값이 0이고 Umbrella_Id2값이 0이면 User의 Umbrella_Id2에 저장

        else return 3;
        // 둘다 0이 아니라면 대여 최대 가능 댓수인 2를 넘어가기 때문에
        // 안내문을 출력하기위해서 1을 return

        log.info("umbrella Information : "+umbrella);
        log.info("User     Information : "+user);
        return 1;
        // 대여가 가능하고 정삭적으로 진행되었다면 1을 return
    }


    @Transactional
    public int returnUmbrella(Long id, User requestUser) {
        Umbrella umbrella = umbrellaRepository.findById(id).orElseGet(() -> {
            return new Umbrella();
        });
        User user= userRepository.findById(requestUser.getId()).orElseGet(() -> {
            return new User();
        });
        log.info("Return User -> "+requestUser);

        if (user.getUmbrella_Id1() == umbrella.getId().intValue()) {
            user.setUmbrella_Id1(0L);
            umbrella.setUser_id(0L);
            umbrella.setReturn_date(Timestamp.valueOf(LocalDateTime.now()) );
        } else if (user.getUmbrella_Id2() == umbrella.getId().intValue()) {
            user.setUmbrella_Id2(0L);
            umbrella.setUser_id(0L);
            umbrella.setReturn_date(Timestamp.valueOf(LocalDateTime.now()) );

        }else return 3;
        log.info("Return umbrella Information : "+umbrella);
        log.info("Return User     Information : "+user);
        // 반납 또한 대여와 동일한 방식으로 진행
        return 1;
    }
}

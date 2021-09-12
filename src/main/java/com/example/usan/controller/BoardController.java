package com.example.usan.controller;


import com.example.usan.model.Board;
import com.example.usan.repository.BoardRepository;
import com.example.usan.service.BoardService;
import com.example.usan.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;
    private BoardRepository boardRepository;
    private UserService userService;


    // 컨트롤러에서 세션을 어떻게 찾는지?
    @GetMapping
    public String index(Model model, @PageableDefault(size = 10, sort = "id") Pageable pageable, HttpServletResponse response) {
        // /WEB-INF/views/joinForm.jsp
        for (int i = 1; i <= 100; i++) {
            Long id = new Long(i);
            Board boardCheck = boardRepository.findById(id).orElseGet(() -> {
                return new Board();
            });
            if (boardCheck.getCreateDate() == null) {
                String count = String.valueOf(id);
                Board createBoard = new Board();
                createBoard.setUser(userService.boardUser(1L));
                createBoard.setTitle(count + "번째 글입니다.");
                createBoard.setContent(count + "번째 글의 내용입니다.");
                boardRepository.save(createBoard);
                log.info("기본 공지글 생성");
            } else {
                log.info(" 이미 공지글이 있습니다.");
                break;
            }

        }

        model.addAttribute("list", boardService.boardList(pageable));

        return "thymeleaf/board/board";
        // BoardController는 REST Controller가 아닌 그냥 Controller이기 때문에
        // 리턴할때 viewResolver가 작동 위에 boards를 라는 이름으로 글목록()을 들고갑니다.

    }

    // 컨트롤러에서 세션을 어떻게 찾는지?
    @GetMapping("test")
    public String indext(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, HttpServletResponse response) {
        // /WEB-INF/views/joinForm.jsp
        for (int i = 1; i <= 20; i++) {
            Long id = new Long(i);
            Board boardCheck = boardRepository.findById(id).orElseGet(() -> {
                return new Board();
            });
            if (boardCheck.getCreateDate() == null) {
                String count = String.valueOf(i);
                Board createBoard = new Board();
                createBoard.setUser(userService.boardUser(1L));
                createBoard.setTitle(count + "번째 글입니다.");
                createBoard.setContent(count + "번째 글의 내용입니다.");
                boardRepository.save(createBoard);
                log.info("기본 공지글 생성");
            } else log.info(" 이미 {}번 공지글이 있습니다.",i);

        }

        model.addAttribute("list", boardService.boardList(pageable));
        return "board/boardList";
        // BoardController는 REST Controller가 아닌 그냥 Controller이기 때문에
        // 리턴할때 viewResolver가 작동 위에 boards를 라는 이름으로 글목록()을 들고갑니다.

    }


    @GetMapping("/home")
    public String home() {
        return "thymeleaf/home";
    }



    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.boardDetail(id));
        boardService.viewCount(id);

        return "board/detail";
    }

    @GetMapping("/{id}/updateForm")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.boardDetail(id));
        return "board/updateForm";
    }

    // USER 권한이 필요
    @GetMapping("/saveForm/admin")
    public String saveForm() {
        return "board/saveForm";
    }


}



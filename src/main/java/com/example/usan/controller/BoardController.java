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

        model.addAttribute("list", boardService.boardList(pageable));

        return "thymeleaf/board/board";
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

        return "thymeleaf/board/board_detail";
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



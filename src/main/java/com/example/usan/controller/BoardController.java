package com.example.usan.controller;


import com.example.usan.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 컨트롤러에서 세션을 어떻게 찾는지?
    @GetMapping({"/board"})
    public String index(Model model ,@PageableDefault(size =3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable) {
        // /WEB-INF/views/joinForm.jsp
        model.addAttribute("boards", boardService.글목록(pageable));
        return "board/boardList";
        //BoardController는 REST Controller가 아닌 그냥 Controller이기 때문에
        // 리턴할때 viewResolver가 작동 위에 boards를 라는 이름으로 글목록()을 들고갑니다.
        //
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.글상세보기(id));
        boardService.조회수증가(id);
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model ){
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/updateForm";
    }

    // USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }


}



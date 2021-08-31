package com.example.usan.controller.api;


import com.example.usan.config.auth.PrincipalDetail;
import com.example.usan.dto.LikeSaveRequestDto;
import com.example.usan.dto.ReplySaveRequestDto;
import com.example.usan.dto.ResponseDto;
import com.example.usan.model.Board;
import com.example.usan.model.Umbrella;
import com.example.usan.model.User;
import com.example.usan.repository.BoardRepository;
import com.example.usan.repository.UserRepository;
import com.example.usan.service.BoardService;
import com.example.usan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@Slf4j
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;
    private final UserService userService;
    private final BoardRepository boardRepository;


    @PostMapping
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        // 공지사항을 저장하는 메소드

        boardService.writeBoard(board, principal.getUser());

        // Board의 내용과 작성 유저의 정보를 DB에 저장
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.boardDelete(id); // 해당 ID의 Board를 DB에서 삭제
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }

    @PatchMapping("/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.boardModify(id, board); // 해당 ID의 Board를 내용을 수정하여 다시 저장
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/{boardId}/like")
    public ResponseDto<Integer> likeUpdate(@PathVariable int boardId, @RequestBody Board board) {
        boardService.likeHit(boardId, board); // 해당 ID의 Board의 좋아요 수를 증가
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/{boardId}/like")
    public ResponseDto<Integer> likeListUpdate(@PathVariable int boardId, @RequestBody LikeSaveRequestDto likeSaveRequestDto) {
        boardService.likeHit(boardId, likeSaveRequestDto);
        // 해당 ID의 Board의 좋아요 수를 증가와 동시에 Like Dto 생성
        log.info(likeSaveRequestDto.toString());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //데이터 받을때 컨트롤러에서 DTO를 만들어서 받는게 좋다.
    @PostMapping("/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
        boardService.writeReply(replySaveRequestDto);
        // DTO(User ID, Board ID, Content)를 받아 Reply 테이블에 저장
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        boardService.replyDelete(replyId);
        // 특정 ID에 해당하는 댓글을 삭제
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}

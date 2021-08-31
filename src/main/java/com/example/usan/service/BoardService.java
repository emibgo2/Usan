package com.example.usan.service;


import com.example.usan.dto.LikeSaveRequestDto;
import com.example.usan.dto.ReplySaveRequestDto;
import com.example.usan.model.Board;
import com.example.usan.model.User;
import com.example.usan.repository.BoardRepository;
import com.example.usan.repository.LikeRepository;
import com.example.usan.repository.ReplyRepository;
import com.example.usan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. loC, 메모리에 띄워줌
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;


    @Autowired
    private LikeRepository likeRepository;

    @Transactional
    public void writeBoard(Board board, User user) { // title, Content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
        // Board의 내용과 작성한 User의 정보를 DB에 저장
    }


    @Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable= PageRequest.of(page,10, Sort.by("id").descending());
        return boardRepository.findAll(pageable);
    }
    // DB내의 Board들을 갖고와서 @PageableDefault 내에서 지정하는 size 만큼 짤라서 return

    @Transactional(readOnly = true)
    public Board boardDetail(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
                });
        // 해당 id 값을 가진 Board의 세부 내용을 return
    }

    @Transactional
    public void boardDelete(int id) {
        boardRepository.deleteById(id);
    }
    // 해당 id 값의 Board를 DB에서 삭제

    @Transactional
    public void boardModify(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
                });
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 됨. DB Flush
        // 해당 id 값의 Board를 새로운 Title, Content를 집어넣어 DB값을 수정
    }

    @Transactional
    public void writeReply(ReplySaveRequestDto replySaveRequestDto){ replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent()); }
    // DB에 기존 Repository Save를 사용하지않고 따로 제작힌 mSave를 사용하여 DB에 저장

    @Transactional
    public void replyDelete(int replyId) {
        replyRepository.deleteById(replyId);
    }
    // 해당 replyId값을 가진 Reply를 DB에서 삭제

    @Transactional
    public void likeHit(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
                });
        board.setBoardLike(requestBoard.getBoardLike()+1);
        // 좋아요를 눌렀을 때 기존 좋아요 수에 +1
    }
    @Transactional
    public void likeHit(int id, LikeSaveRequestDto likeSaveRequestDto) { likeRepository.lSave(likeSaveRequestDto.getUserId(), likeSaveRequestDto.getBoardId()); }
    // 좋아요를 누른 User와 눌려진 Board의 정보를 따로 저장

    @Transactional
    public void viewCount(int id ) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
                });
        board.setCount(board.getCount()+1);
        // 사용자들이 해당 Board Detail에 접근 할때마다 Count를 +1 씩 증가
        // Debuging 필요 ( 중복 조회수 금지 )
    }
}
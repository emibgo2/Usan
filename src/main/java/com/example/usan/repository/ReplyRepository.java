package com.example.usan.repository;


import com.example.usan.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    @Modifying
    @Query(value = "INSERT INTO reply(userId,boardId,content,createDate) VALUES(?1,?2,?3,now())",nativeQuery = true)
    int mSave(Long userId, Long boardId,String cotent) ;

}

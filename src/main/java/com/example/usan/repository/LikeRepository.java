package com.example.usan.repository;

import com.example.usan.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikeRepository extends JpaRepository<Reply,Integer> {

    @Modifying
    @Query(value = "INSERT INTO boardlike(userId,boardId) VALUES(?1,?2)",nativeQuery = true)
    int lSave(int userId, int boardId) ;

}

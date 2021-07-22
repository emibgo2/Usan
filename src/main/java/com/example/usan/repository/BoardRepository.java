package com.example.usan.repository;


import com.example.usan.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;


// JSP로 치면 DAO
// 자동으로 bean 들고이 된다.
// @Repository 생략 가능
public interface BoardRepository extends JpaRepository<Board, Integer> {


}

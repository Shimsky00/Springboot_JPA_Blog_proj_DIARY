package com.shim.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shim.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}

package com.example.base.repository;

import com.example.base.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ThangDH
 */
/**
 * Class kế thừa JpaRepository để dùng các hàm tiện ích của spring hibernate
 */

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByUserId(Long userId);
}

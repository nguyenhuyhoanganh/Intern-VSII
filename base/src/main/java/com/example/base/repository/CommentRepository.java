package com.example.base.repository;

import com.example.base.dto.CommentDTO;
import com.example.base.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author ThangDH
 */
/**
 * Class kế thừa JpaRepository để dùng các hàm tiện ích của spring hibernate
 */

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByUserId(Long userId);


    @Query(value = "CALL getCommentByUserID(:idUser)",nativeQuery = true)
    List<Comment> getCommentByUserID(@PathVariable("idUser") Long idUser);
    @Query(value = "CALL getAllComment()",nativeQuery = true)
    List<Comment> getAllComment();

    @Query(value = "CALL sp_addComment(:i_idUser, :i_content, :i_createcmtday, @o_commentID);", nativeQuery = true)
    Comment addComment(
            @Param("i_idUser") Long userId,
            @Param("i_content") String content,
            @Param("i_createcmtday") LocalDate createCmtDay
    );

    @Query(value = "SELECT @o_commentID", nativeQuery = true)
    Long getCommentIdAfterAdd();
    @Query(value = "CALL sp_updateComment(:i_commentID, :i_newContent, :i_createcmtday);", nativeQuery = true)
    Comment updateComment(
            @Param("i_commentID") Long commentID,
            @Param("i_newContent") String newContent,
            @Param("i_createcmtday") LocalDate createCmtDay
    );
    @Query(value = "CALL deleteCommentByID(:i_ID)", nativeQuery = true)
    void deleteCommentByID(@Param("i_ID") Long commentID);
}

package com.example.base.service;

import com.example.base.dto.CommentDTO;
import com.example.base.entity.Comment;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ThangDH
 */
public interface ICommentService {
    List<CommentDTO> getAll();
    List<CommentDTO> findCommentByUserId(Long idUser);
    CommentDTO insert(CommentDTO commentDTO);
    CommentDTO updateComment( String newContent, LocalDate createCmtDay, Long id);
    void deleteByUserId(Long id);

}

package com.example.base.service;

import com.example.base.dto.CommentDTO;

import java.util.List;

/**
 * @author ThangDH
 */
public interface ICommentService {
    List<CommentDTO> getAll();
    List<CommentDTO> findCommentByUserId(Long idUser);
    CommentDTO insert(CommentDTO commentDTO);
    CommentDTO update(CommentDTO commentDTO,Long id);
    void deleteByUserId(Long id);
}

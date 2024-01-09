package com.example.base.utils;

import com.example.base.entity.Comment;
import com.example.base.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author ThangDH
 */
@Component
@RequiredArgsConstructor
public class CommentUtils {
    private static final ModelMapper modelMapper = new ModelMapper();
    /**
     * Cung cấp hàm để mapCommentDtoToComment để chuyển đổi dữ liệu CommentDTO sang  Comment.
     * @param commentDTO  truyền vào.
     * @return về entity Comment.
     */
    public static Comment mapCommentDtoToComment(CommentDTO commentDTO){
        return modelMapper.map(commentDTO,Comment.class);
    }
    /**
     * Cung cấp hàm để mapCommentToCommentDTo để chuyển đổi dữ liệu Comment sang CommentDTO.
     *
     * @param comment  truyền vào.
     * @return trả về CommentDTO.
     */
    public static CommentDTO mapCommentToCommentDTO(Comment comment){
        return modelMapper.map(comment,CommentDTO.class);
    }
}

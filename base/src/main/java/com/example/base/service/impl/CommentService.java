package com.example.base.service.impl;

import com.example.base.constant.CommentConstant;
import com.example.base.constant.UserConstant;
import com.example.base.entity.Comment;
import com.example.base.entity.User;
import com.example.base.exception.domain.UserNotFoundException;
import com.example.base.dto.CommentDTO;
import com.example.base.repository.CommentRepository;
import com.example.base.repository.UserRepository;
import com.example.base.service.ICommentService;
import com.example.base.utils.CommentUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author ThangDH
 */
/**
 * Class xử lý CRUD liên quan đến bảng Comment và được kế thừa ra từ Interface ICommentService
 */

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService{

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;
    /**
     *  Gọi hàm mapCommentToCommentDTO trong CommentUtils để chuyển Comment -> CommentDTO
     * @return  trả về 1 danh sach CommentDTO
     */

    @Override
    public List<CommentDTO> getAll() {
        return commentRepository.getAllComment().stream()
                .map(comment->CommentUtils.mapCommentToCommentDTO(comment)).toList();
    }
    /**
     *  Gọi hàm mapCommentToCommentDTO trong CommentUtils để chuyển Comment -> CommentDTO
     * @param  idUser truền vào id của user
     * @return  trả về 1 danh sach CommentDTO theo id của user
     */
    @Override
    public List<CommentDTO> findCommentByUserId(Long idUser) {
        return commentRepository.getCommentByUserID(idUser).stream()
                .map(comment->CommentUtils.mapCommentToCommentDTO(comment)).toList();
    }
    /**
     * @param commentDTO truyền vào toàn bộ thông tin của comment như ,id ,content,ngày tạo,idUser
     * Kiểm tra xem có user nào mà có id bằng với idUser của comment đó
     * Case1:Trả về 1 exception Không thìm thấy user có id như thế
     * Case2:tạo ra một commnent mới rồi xét user của Comment bằng user đươợc tìm thấy ở trên
     * @return trả về thông tin comment và trạng thái thành công
     */
    @Override
    @Transactional
    public CommentDTO insert(CommentDTO commentDTO) {
        Long userId = commentDTO.getUserID();
        String content = commentDTO.getContent();
        LocalDate createCmtDay = commentDTO.getCreateCMTDay();

        // Gọi stored procedure để chèn comment
        Comment insertedComment = commentRepository.addComment(userId, content, createCmtDay);

        // Lấy thông tin comment sau khi chèn thành công
        Long commentId = commentRepository.getCommentIdAfterAdd();

        if (commentId != null) {
            Optional<Comment> retrievedComment = commentRepository.findById(commentId);

            if (retrievedComment.isPresent()) {
                return CommentUtils.mapCommentToCommentDTO(retrievedComment.get());
            } else {
                throw new RuntimeException("Lỗi khi lấy thông tin comment sau khi chèn.");
            }
        } else {
            throw new RuntimeException("Chèn không thành công");
        }
    }
    /**
     * @param id truyền vào id của comment và toàn bộ thông tin của comment như content,ngày tạo,idUser
     *  Tìm kiếm Comment theo id của người dùng
     *  Kiểm tra nếu tồn tại Comment theo id của người dùng
     * Case1: @return trả về thông tin comment và trạng thái thành công
     * Case2: Trả về Exception không tìm thấy Comment
     */
    @Override
    @Transactional
    public CommentDTO updateComment( String newContent, LocalDate createCmtDay, Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment updatedComment = commentRepository.updateComment(id, newContent, createCmtDay);

            if (updatedComment != null) {
                return CommentUtils.mapCommentToCommentDTO(updatedComment);
            } else {
                throw new RuntimeException("Lỗi khi cập nhật comment");
            }
        } else {
            throw new RuntimeException(CommentConstant.COMMENT_NOT_FOUND);
        }
    }
    /**
     * @param id truyền vào id của comment
     * Kiểm tra xem có tồn tại Comment theo id nó không
     * Case1:Nếu có thì @return trạng thái đã xóa thành công
     * Case2: Nếu không có comment như trên thì in ra exception không tìm thấy comment
     */
    @Override
    public void deleteByUserId(Long id) {
        Optional<Comment> optionalComment=commentRepository.findById(id);
        if(optionalComment.isPresent()){
            commentRepository.deleteCommentByID(id);
        }else{
            throw new RuntimeException(CommentConstant.COMMENT_NOT_FOUND);
        }
    }
}

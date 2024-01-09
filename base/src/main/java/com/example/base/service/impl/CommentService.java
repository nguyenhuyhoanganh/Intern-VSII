package com.example.base.service.impl;

import com.example.base.constant.CommentConstant;
import com.example.base.constant.UserConstant;
import com.example.base.entity.Comment;
import com.example.base.entity.User;
import com.example.base.exception.domain.UserNotFoundException;
import com.example.base.model.CommentDTO;
import com.example.base.repository.CommentRepository;
import com.example.base.repository.UserRepository;
import com.example.base.service.ICommentService;
import com.example.base.utils.CommentUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return commentRepository.findAll().stream()
                .map(comment->CommentUtils.mapCommentToCommentDTO(comment)).toList();
    }
    /**
     *  Gọi hàm mapCommentToCommentDTO trong CommentUtils để chuyển Comment -> CommentDTO
     * @param  idUser truền vào id của user
     * @return  trả về 1 danh sach CommentDTO theo id của user
     */
    @Override
    public List<CommentDTO> findCommentByUserId(Long idUser) {
        return commentRepository.findByUserId(idUser).stream()
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
        User user=userRepository.findById(commentDTO.getUserID())
                .orElseThrow(() -> new UserNotFoundException(UserConstant.USER_NOT_FOUND_BY_ID));
        Comment newComment = CommentUtils.mapCommentDtoToComment(commentDTO);
        newComment.setUser(user);
        return CommentUtils.mapCommentToCommentDTO(commentRepository.save(newComment));
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
    public CommentDTO update(CommentDTO commentDTO, Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            Comment updateComment = CommentUtils.mapCommentDtoToComment(commentDTO);
            updateComment.setId(id);
            updateComment.setUser(comment.getUser());
            return CommentUtils.mapCommentToCommentDTO(commentRepository.save(updateComment));
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
    @Transactional
    public void deleteByUserId(Long id) {
        Optional<Comment> optionalComment=commentRepository.findById(id);
        if(optionalComment.isPresent()){
            commentRepository.delete(optionalComment.get());
        }else{
            throw new RuntimeException(CommentConstant.COMMENT_NOT_FOUND);
        }
    }
}

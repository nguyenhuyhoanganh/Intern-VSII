package com.example.base.controller;

import com.example.base.model.CommentDTO;
import com.example.base.model.ResponseDTO;
import com.example.base.service.impl.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ThangDH
 */
@Controller
@RestController
@RequiredArgsConstructor
public class CommentController {
    @Autowired
    private final CommentService commentService;
    /**
     *API trả về toàn bộ comment
     * @return về danh sách comment
     */
    @GetMapping("/comment")
    public ResponseDTO<List<CommentDTO>> getAll(){
        return ResponseDTO.<List<CommentDTO>>builder().data(commentService.getAll()).build();
    }
    @GetMapping("/comment/{idUser}")
    /**
     *API trả về  comment của người dùng theo id
     * @param idUser truyền vào id của người dùng
     * @return trả về toàn bộ commnet cua người dùng với id được truyền vào
     */
    public ResponseDTO<List<CommentDTO>> view(@PathVariable("idUser") Long idUser) {
        return ResponseDTO.<List<CommentDTO>>builder().
                data(commentService.findCommentByUserId(idUser)).build();
    }
    /**
     *API sửa  comment của người dùng theo id của comment
     * @param id truyền vào id của comment và toàn bộ thông tin của comment như content,ngày tạo,idUser
     * @return trả về thông tin comment và trạng thái thành công
     */
    @PutMapping("/comment/update/{id}")
    public ResponseEntity<ResponseDTO<CommentDTO>> updateAddress(@Valid @RequestBody CommentDTO commentDTO, @PathVariable Long id) {
        ResponseDTO<CommentDTO> responseDTO= ResponseDTO.<CommentDTO>builder()
                .data(commentService.update(commentDTO,id))
                .code(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    /**
     *API thêm  comment của người dùng theo id của comment
     * @param commentDTO truyền vào toàn bộ thông tin của comment như ,id ,content,ngày tạo,idUser
     * @return trả về thông tin comment và trạng thái thành công
     */
    @PostMapping("/comment/add")
    public ResponseEntity<ResponseDTO<CommentDTO>> create(@Valid @RequestBody CommentDTO commentDTO) {

        ResponseDTO<CommentDTO> responseDTO= ResponseDTO.<CommentDTO>builder()
                .data(commentService.insert(commentDTO))
                .code(HttpStatus.CREATED.value())
                .build();
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }
    /**
     *API xóa  comment của người dùng theo id của comment
     * @param id truyền vào id của comment
     * @return trạng thái đã xóa thành công
     */
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<ResponseDTO<CommentDTO>> deleteById(@PathVariable Long id) {
        commentService.deleteByUserId(id);
        ResponseDTO<CommentDTO> responseDTO = ResponseDTO.<CommentDTO>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDTO);
    }
}

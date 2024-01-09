package com.example.base.dto;

import com.example.base.constant.CommentConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

/**
 * @author ThangDH
 */
/**
 * Class chuyển đổi dũ liệu entity comment
 * Có các thông tin như:id,content,ngày tạo,id của user
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;

    @NotBlank(message = CommentConstant.CONTENT)
    private String content;

    @NotNull(message = CommentConstant.CREATE_COMMENT_DAY)
    private LocalDate createCMTDay;

    @JsonProperty("id_user")
    private Long userID;
}

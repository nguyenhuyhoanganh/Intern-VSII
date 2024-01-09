package com.example.base.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author AnhNHH
 *
 * ResponseDTO là một class Generic đại diện cho Respone trả về bởi ứng dụng.
 * Nó bao gồm các chi tiết như HTTP status code, timestamp, message, thông tin pagination (page và limit),
 * và nội dung data hoặc error mang kiểu dữ liệu <T>.
 *
 * @param <T> là kiểu dữ liệu cho data hoặc error.
 * Custom format của respónse cho thống nhất
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO<T> {
    /**
     * HTTP status code. Mặc định có giá trị là HttpStatus.OK.value().
     */
    @Builder.Default
    private int code = HttpStatus.OK.value();
    /**
     * Timestamp cho biết thời điểm mà response được tạo. Mặc định có giá trị timestamp hiện tại.
     */
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    /**
     * Một message cung cấp thông tin bố sung về response.
     * Trường này được thêm vào response nếu nó not null.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * Page number cho responses có phân trang.
     * Trường này được thêm vào response nếu nó not null.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer page;

    /**
     * Giới hạn số lượng items mỗi page trong response có phân trang.
     * Trường này được thêm vào response nếu nó not null.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer limit;

    /**
     * data với kiểu dữ liệu <T>.
     * Trường này được thêm vào response nếu nó not null.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    /**
     * error với kiểu dữ liệu <T>.
     * Trường này được thêm vào response nếu nó not null.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T error;

}

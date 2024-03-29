package com.example.base.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * format kiểu trả về của token
 * @author HungDV
 */
@Builder
@Data
@AllArgsConstructor
public class TokenDTO {
    private String token;
}

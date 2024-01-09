package com.example.base.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author HungDV
 */
@Builder
@Data
@AllArgsConstructor
public class TokenDTO {
    private String token;
}

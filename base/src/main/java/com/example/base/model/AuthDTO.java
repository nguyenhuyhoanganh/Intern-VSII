package com.example.base.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author HungDV
 * Class tiếp nhận req body và trả về theo formmat
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDTO {
    private String username;
    private String authenticationCode;
}

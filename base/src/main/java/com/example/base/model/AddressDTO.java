package com.example.base.model;

import com.example.base.constant.AddressConstant;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Phuong Oanh
 * Class tiếp nhận req body và trả về theo formmat
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long id;

    @NotBlank(message = AddressConstant.LINE_NOT_BLANK)
    private String line;
    @NotBlank(message = AddressConstant.WARD_NOT_BLANK)
    private String ward;

    @NotBlank(message = AddressConstant.DISTRICT_NOT_BLANK)
    private String district;

    @NotBlank(message = AddressConstant.PROVINCE_NOT_BLANK)
    private String province;

    private Long userId;

}

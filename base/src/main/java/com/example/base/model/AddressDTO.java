package com.example.base.model;

import com.example.base.constant.AddressConstant;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Phuong Oanh
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

    @NotBlank(message = AddressConstant.DISTRICT_NOT_BLANK)
    private String district;

    @NotBlank(message = AddressConstant.PROVINCE_NOT_BLANK)
    private String province;

    @NotBlank(message = AddressConstant.WARD_NOT_BLANK)
    private String ward;

    private Long userId;

}

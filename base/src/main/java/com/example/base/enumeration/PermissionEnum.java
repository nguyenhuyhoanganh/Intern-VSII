package com.example.base.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

/**
 * @author HungDV
 */
@RequiredArgsConstructor
public enum PermissionEnum {
    ADMIN_VIEW_ALL_USERS("/users",PermissionMethodEnum.GET), // xem danh sách user
    ADMIN_CREATE_USER("/users",PermissionMethodEnum.POST), // tạo mới user
    ADMIN_UPDATE_USER("/users/*",PermissionMethodEnum.PUT), // update user
    ADMIN_VIEW_USER_DETAILS("/users/*",PermissionMethodEnum.GET), // xem user chi tiết
    USER_VIEW_USER_DETAILS("/users/*",PermissionMethodEnum.GET) // xem user chi tiết nhưng chỉ xem chính nó
    ;
    @Getter
    private final String url;

    @Getter
    private final PermissionMethodEnum method;

}

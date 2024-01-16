package com.example.base.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

/**
 * @author HungDV
 */
public enum PermissionEnum {
//    ADMIN_VIEW_ALL_USERS("/users",PermissionMethodEnum.GET), // xem danh sách user
//    ADMIN_CREATE_USER("/users",PermissionMethodEnum.POST), // tạo mới user
//    ADMIN_UPDATE_USER("/users/*",PermissionMethodEnum.PUT), // update user
//    ADMIN_VIEW_USER_DETAILS("/users/*",PermissionMethodEnum.GET), // xem user chi tiết
//    USER_VIEW_USER_DETAILS("/users/*",PermissionMethodEnum.GET) // xem user chi tiết nhưng chỉ xem chính nó

    ADMIN_VIEW_ALL_USERS, // xem danh sách user
    ADMIN_CREATE_USER, // tạo mới user
    ADMIN_UPDATE_USER, // update user
    ADMIN_VIEW_USER_DETAILS, // xem user chi tiết
    USER_VIEW_USER_DETAILS // xem user chi tiết nhưng chỉ xem chính nó
}

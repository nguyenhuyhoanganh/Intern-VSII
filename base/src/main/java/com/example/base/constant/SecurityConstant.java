package com.example.base.constant;

/**
 * @author HungDV
 */
public class SecurityConstant {
    public static final String[] PRIVATE_URIS_ROLE_USER= {"/users/*"};
    public static final String[] PRIVATE_URIS_ROLE_ADMIN= {"/auth/admin/**","/users/**","/users"};

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String PREFIX_ROLE_NAME = "ROLE_";
    public static final String INDEX_OF_ = "_";
    public static final int  LAST_INDEX_PREFIX_TOKEN = 7;

    public static final int TOKEN_EXPIRATION = 1000 * 60 * 30; // 30p
}

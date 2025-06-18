package com.phynos.solar.auth.jwttoken.util;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/3/22 11:21
 */
public class BearerTokenUtil {

    public static final String TOKEN_HEAD = "Bearer ";
    public static final String TOKEN_HEADER = "Authorization";

    public static String getAuthToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith(TOKEN_HEAD)) {
            return null;
        }
        final String authToken = authHeader.substring(TOKEN_HEAD.length()); // The part after "Bearer "
        return authToken;
    }

}

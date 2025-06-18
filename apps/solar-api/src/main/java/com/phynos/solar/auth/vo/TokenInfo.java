package com.phynos.solar.auth.vo;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.phynos.solar.auth.user.UserDetails;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * token解码后的一些基本数据
 * 此部分数据不需要额外查询
 *
 * @author lupc
 * @date 2022/4/1 16:53
 */
@Data
public class TokenInfo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户姓名
     */
    private String realname;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long tenantId;
    private String tenantCode;
    private String tenantName;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long deptId;
    private String deptName;

    private String openid;
    private String unionid;

    public static TokenInfo fromJWT(DecodedJWT jwt) {
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserId(jwt.getClaim("id").asLong());
        tokenInfo.setUsername(jwt.getClaim("username").asString());
        tokenInfo.setRealname(jwt.getClaim("realname").asString());
        tokenInfo.setTenantId(jwt.getClaim("tenantId").asLong());
        tokenInfo.setTenantCode(jwt.getClaim("tenantCode").asString());
        tokenInfo.setTenantName(jwt.getClaim("tenantName").asString());
        return tokenInfo;
    }

    public static TokenInfo fromUserDetails(UserDetails userDetails) {
        TokenInfo vo = new TokenInfo();
        vo.setUsername(userDetails.getUsername());
        vo.setTenantId(userDetails.getTenantId());
        return vo;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();//用来存放payload
        map.put("id", String.valueOf(userId));
        map.put("username", username);
        map.put("realname", realname);
        map.put("tenantId", String.valueOf(tenantId));
        map.put("tenantCode", tenantCode);
        map.put("tenantName", tenantName);
        return map;
    }

}

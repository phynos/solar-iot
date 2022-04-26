package com.phynos.solar.auth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * token解码后的一些基本数据
 * 此部分数据不需要额外查询
 *
 * @author lupc
 * @date 2022/4/1 16:53
 */
@Data
public class TokenInfo {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户姓名
     */
    private String realname;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long tenantId;

    private String tenantCode;

    private String tenantName;

}

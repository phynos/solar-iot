package com.phynos.solar.auth.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.phynos.solar.auth.user.UserDetails;
import lombok.Data;

/**
 * token解码后，进一步查询的信息
 *
 * @author lupc
 * @date 2022/3/22 11:18
 * @see TokenInfo
 */
@Data
public class LoginUserVO {

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

    /**
     * openid
     */
    private String openid;
    /**
     * unionid
     */
    private String unionid;

    public static LoginUserVO fromUserDetails(UserDetails userDetails) {
        LoginUserVO vo = new LoginUserVO();
        vo.setUsername(userDetails.getUsername());
        vo.setTenantId(userDetails.getTenantId());
        return vo;
    }

}

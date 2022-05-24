package com.phynos.solar.auth.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.phynos.solar.module.sys.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

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

    public static LoginUserVO fromDO(User user) {
        LoginUserVO vo = new LoginUserVO();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }

}

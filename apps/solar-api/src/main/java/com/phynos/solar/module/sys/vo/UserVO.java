package com.phynos.solar.module.sys.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2021/7/16 10:39
 */
@Data
public class UserVO {

    /**
     * 用户id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 性别 0：男 1：女 2：保密
     */
    private String gender;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片
     */
    private String image;

    /**
     * 最后访问时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 启用状态
     */
    private Boolean enable;

    /**
     * 微信公众号-用户唯一标志
     */
    private String openid;

    /**
     * 微信公众号
     */
    private String unionid;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 租户CODE
     */
    private String tenantCode;
}

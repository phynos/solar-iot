package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lupc
 * @since 2022-04-24
 */
@Getter
@Setter
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码MD5
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private Long modifyUserId;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

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
     * 盐加密
     */
    private String salt;

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

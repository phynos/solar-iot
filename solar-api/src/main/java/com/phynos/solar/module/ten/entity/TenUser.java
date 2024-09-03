package com.phynos.solar.module.ten.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2024-09-03
 */
@Getter
@Setter
@TableName("ten_user")
public class TenUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Long id;

    /**
     * 租户id
     */
    @TableField("tenant_id")
    private Long tenantId;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 盐加密
     */
    @TableField("salt")
    private String salt;

    /**
     * 电话号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 性别 0：男 1：女 2：保密
     */
    @TableField("sex")
    private String sex;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 启用状态
     */
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 微信公众号-用户唯一标志
     */
    @TableField("openid")
    private String openid;

    /**
     * 微信公众号
     */
    @TableField("unionid")
    private String unionid;

    /**
     * 最后访问时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 头像路径
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 真实姓名
     */
    @TableField("realname")
    private String realname;

    /**
     * 删除标记位
     */
    @TableField("del_flag")
    private Boolean delFlag;

    /**
     * 最后访问IP
     */
    @TableField("login_ip")
    private String loginIp;

    /**
     * 电子邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户类型
     */
    @TableField("user_type")
    private String userType;

    /**
     * 创建者
     */
    @TableField("create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @TableField("update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}

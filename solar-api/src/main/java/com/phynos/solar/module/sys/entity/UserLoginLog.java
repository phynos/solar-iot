package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 管理用户登录日志表
 * </p>
 *
 * @author lupc
 * @since 2021-12-20
 */
@Getter
@Setter
@TableName("sys_user_login_log")
public class UserLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 登录时间
     */
    private LocalDateTime loginDatetime;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 登录平台
     */
    private Integer platform;


}

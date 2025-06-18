package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 登录日志表
 * </p>
 *
 * @author lupc
 * @since 2024-09-03
 */
@Getter
@Setter
@TableName("sys_log_login")
public class SysLogLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("user_id")
    private Long userId;

    @TableField("login_ip")
    private String loginIp;

    @TableField("platform")
    private Integer platform;
}

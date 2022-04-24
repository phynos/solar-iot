package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lupc
 * @since 2022-04-24
 */
@Getter
@Setter
@TableName("sys_log_audit")
public class LogAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 模块名称
     */
    private String moduleTitle;

    /**
     * 操作类型
     */
    private String actionName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 方法参数
     */
    private String methodParams;

    /**
     * 操作终端
     */
    private Integer platform;

    private Long operUserId;

    private String operUrl;

    private String operIp;

    private String operLocation;

    private Integer errorCode;

    private String errorMsg;

    private Boolean status;


}

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
 * 操作审计表
 * </p>
 *
 * @author lupc
 * @since 2024-09-03
 */
@Getter
@Setter
@TableName("sys_log_audit")
public class SysLogAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 操作时间
     */
    @TableField("oper_time")
    private LocalDateTime operTime;

    /**
     * 模块标题
     */
    @TableField("titile")
    private String titile;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @TableField("business_type")
    private Integer businessType;

    /**
     * 方法名称
     */
    @TableField("method")
    private String method;

    /**
     * 请求方式
     */
    @TableField("request_method")
    private String requestMethod;

    /**
     * 操作终端
     */
    @TableField("platform")
    private Integer platform;

    /**
     * 操作人员
     */
    @TableField("oper_name")
    private String operName;

    /**
     * 部门名称
     */
    @TableField("dept_name")
    private String deptName;

    /**
     * 请求URL
     */
    @TableField("oper_url")
    private String operUrl;

    /**
     * 主机地址
     */
    @TableField("oper_ip")
    private String operIp;

    /**
     * 操作地点
     */
    @TableField("oper_location")
    private String operLocation;

    /**
     * 操作状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 请求参数
     */
    @TableField("oper_param")
    private String operParam;

    /**
     * 返回参数
     */
    @TableField("json_result")
    private String jsonResult;

    /**
     * 错误消息
     */
    @TableField("error_msg")
    private String errorMsg;
}

package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 操作记录表
 * </p>
 *
 * @author lupc
 * @since 2021-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_operation_log")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 模块标题
     */
    private String moduleTitle;

    /**
     * 功能名称
     */
    private String actionName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 请求参数
     */
    private String methodParams;

    /**
     * 操作状态：0=正常 1=异常）
     */
    private Boolean status;

    /**
     * 操作用户id
     */
    private String operUserId;

    /**
     * 请求URL
     */
    private String operUrl;

    /**
     * 主机地址
     */
    private String operIp;

    /**
     * 操作平台
     */
    private String operFlatform;

    /**
     * 操作地点
     */
    private String operLocation;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    private Date operationDatetime;


}

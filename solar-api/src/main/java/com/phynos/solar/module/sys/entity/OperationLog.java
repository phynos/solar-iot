package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 操作记录表
 * </p>
 *
 * @author lupc
 * @since 2021-12-13
 */
@Getter
@Setter
@TableName("sys_operation_log")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作时间
     */
    private Date createTime;

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
     * 操作终端
     */
    private Integer flatform;

    /**
     * 操作用户id
     */
    private Long operUserId;

    /**
     * 请求URL
     */
    private String operUrl;

    /**
     * 主机地址
     */
    private String operIp;

    /**
     * 操作地点
     */
    private String operLocation;

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作状态：0=正常 1=异常）
     */
    private Boolean status;


}

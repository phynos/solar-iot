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
 * 系统参数表
 * </p>
 *
 * @author lupc
 * @since 2021-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_parameter")
public class Parameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 参数标签
     */
    private String paraLabel;

    /**
     * 参数键名
     */
    private String paraKey;

    /**
     * 参数键值
     */
    private String paraValue;

    /**
     * 参数类型：0=系统内置（不可删），1=用户新增
     */
    private String paraType;

    /**
     * 数据创建时间
     */
    private Date createdDatetime;

    /**
     * 创建用户id
     */
    private String createdUserId;

    /**
     * 更新时间
     */
    private Date updateDatetime;

    /**
     * 更新用户id
     */
    private String updateUserId;

    /**
     * 描述信息
     */
    private String remark;


}

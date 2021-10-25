package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 区域表格
 * </p>
 *
 * @author lupc
 * @since 2021-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_area")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 父区域id
     */
    @TableField("parentId")
    private String parentid;

    /**
     * 排序编号
     */
    private String sort;

    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 创建用户id
     */
    private String createdUserId;

    /**
     * 数据创建时间
     */
    private Date createdDatetime;

    /**
     * 更新用户id
     */
    private String updateUserId;

    /**
     * 更新时间
     */
    private Date updateDatetime;

    /**
     * 描述信息
     */
    private String remark;

    /**
     * 是否被移除
     */
    private Boolean removed;


}

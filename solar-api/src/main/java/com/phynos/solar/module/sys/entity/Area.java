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
 * 区域表格
 * </p>
 *
 * @author lupc
 * @since 2021-12-13
 */
@Getter
@Setter
@TableName("sys_area")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 父区域id
     */
    private Long parentId;

    /**
     * 排序编号
     */
    private Integer sort;

    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 创建用户id
     */
    private Long createdUserId;

    /**
     * 数据创建时间
     */
    private Date createdDatetime;

    /**
     * 更新用户id
     */
    private Long updateUserId;

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

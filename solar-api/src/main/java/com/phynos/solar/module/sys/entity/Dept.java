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
 * 机构表
 * </p>
 *
 * @author lupc
 * @since 2021-12-13
 */
@Getter
@Setter
@TableName("sys_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 数据创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序编号
     */
    private Integer sort;

    /**
     * 删除标志
     */
    private Boolean delFlag;


}

package com.phynos.solar.module.ten.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author lupc
 * @since 2022-11-03
 */
@Getter
@Setter
@TableName("ten_dept")
public class TenDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Long id;

    /**
     * 租户id
     */
    @TableField("tenant_id")
    private Long tenantId;

    /**
     * 部门名称
     */
    @TableField("name")
    private String name;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 负责人
     */
    @TableField("manager")
    private String manager;

    /**
     * 联系电话
     */
    @TableField("tel")
    private String tel;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 部门状态
     */
    @TableField("status")
    private Boolean status;

    /**
     * 组织层级
     */
    @TableField("dept_type")
    private String deptType;

    /**
     * 主键
     */
    @TableField("pid")
    private Long pid;
}

package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 企业部门表
 * </p>
 *
 * @author lupc
 * @since 2022-05-23
 */
@Getter
@Setter
@TableName("sys_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 负责人
     */
    private String manager;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门状态
     */
    private Boolean status;

    /**
     * 组织层级
     */
    private String deptType;

    /**
     * 父id
     */
    private Long pid;


}

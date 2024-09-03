package com.phynos.solar.module.ten.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author lupc
 * @since 2024-09-03
 */
@Getter
@Setter
@TableName("ten_menu")
public class TenMenu implements Serializable {

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
     * 父菜单id
     */
    @TableField("pid")
    private String pid;

    /**
     * 名称
     */
    @TableField("menu_name")
    private String menuName;

    @TableField("menu_path")
    private String menuPath;

    @TableField("code")
    private String code;

    /**
     * 菜单层级
     */
    @TableField("level")
    private Integer level;

    /**
     * 组件
     */
    @TableField("component")
    private String component;
}

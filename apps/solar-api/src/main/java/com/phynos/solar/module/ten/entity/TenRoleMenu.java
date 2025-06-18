package com.phynos.solar.module.ten.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author lupc
 * @since 2024-09-03
 */
@Getter
@Setter
@TableName("ten_role_menu")
public class TenRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("role_id")
    private Long roleId;

    @TableField("menu_id")
    private Long menuId;
}

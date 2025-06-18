package com.phynos.solar.module.ten.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author lupc
 * @since 2024-09-03
 */
@Getter
@Setter
@TableName("ten_role")
public class TenRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 租户id
     */
    @TableField("tenant_id")
    private Long tenantId;

    /**
     * 创建时间
     */
    @TableField("create_at")
    private LocalDateTime createAt;

    /**
     * 创建人ID
     */
    @TableField("create_user_id")
    private Long createUserId;

    /**
     * 创建人-姓名
     */
    @TableField("create_realname")
    private String createRealname;

    /**
     * 修改时间
     */
    @TableField("modify_at")
    private LocalDateTime modifyAt;

    /**
     * 角色名
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 角色编码
     */
    @TableField("role_code")
    private String roleCode;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}

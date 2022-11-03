package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 租户配置表
 * </p>
 *
 * @author lupc
 * @since 2022-11-03
 */
@Getter
@Setter
@TableName("sys_tenant_config")
public class SysTenantConfig implements Serializable {

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
}

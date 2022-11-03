package com.phynos.solar.module.ten.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 岗位表
 * </p>
 *
 * @author lupc
 * @since 2022-11-03
 */
@Getter
@Setter
@TableName("ten_post")
public class TenPost implements Serializable {

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

    @TableField("post_code")
    private String postCode;

    @TableField("post_name")
    private String postName;

    @TableField("sort")
    private Integer sort;

    @TableField("status")
    private Boolean status;
}

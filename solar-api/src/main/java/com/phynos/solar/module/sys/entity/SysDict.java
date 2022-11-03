package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author lupc
 * @since 2022-11-03
 */
@Getter
@Setter
@TableName("sys_dict")
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("create_user_id")
    private Long createUserId;

    /**
     * 字典名称
     */
    @TableField("name")
    private String name;

    /**
     * 字典编码
     */
    @TableField("code")
    private String code;

    /**
     * 状态
     */
    @TableField("status")
    private Boolean status;

    /**
     * 备注
     */
    @TableField("note")
    private String note;
}

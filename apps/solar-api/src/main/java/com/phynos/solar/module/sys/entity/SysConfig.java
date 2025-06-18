package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统配置表
 * </p>
 *
 * @author lupc
 * @since 2024-09-03
 */
@Getter
@Setter
@TableName("sys_config")
public class SysConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 参数名称
     */
    @TableField("config_name")
    private String configName;

    /**
     * 参数键名
     */
    @TableField("config_key")
    private String configKey;

    /**
     * 参数键值
     */
    @TableField("config_value")
    private String configValue;

    /**
     * 系统内置（Y是 N否）
     */
    @TableField("config_type")
    private Integer configType;
}

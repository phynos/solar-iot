package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * OSS对象业务关联表
 * </p>
 *
 * @author lupc
 * @since 2022-11-03
 */
@Getter
@Setter
@TableName("sys_file_biz")
public class SysFileBiz implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 业务id
     */
    @TableField("biz_id")
    private Long bizId;

    /**
     * 文件id
     */
    @TableField("file_id")
    private Long fileId;

    /**
     * 关联的业务表
     */
    @TableField("biz_table")
    private String bizTable;
}

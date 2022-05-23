package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 隐患文件中间表
 * </p>
 *
 * @author lupc
 * @since 2022-05-23
 */
@Getter
@Setter
@TableName("sys_file_biz")
public class FileBiz implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 业务id
     */
    private Long bizId;

    /**
     * 文件id
     */
    private Long fileId;

    /**
     * 关联的业务表
     */
    private String bizTable;


}

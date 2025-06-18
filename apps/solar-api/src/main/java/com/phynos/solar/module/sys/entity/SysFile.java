package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * OSS对象信息表
 * </p>
 *
 * @author lupc
 * @since 2024-09-03
 */
@Getter
@Setter
@TableName("sys_file")
public class SysFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Long id;

    /**
     * 文件真实名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件大小
     */
    @TableField("file_size")
    private Long fileSize;

    /**
     * 文件后缀名
     */
    @TableField("file_extension")
    private String fileExtension;

    /**
     * 文件位置或URL
     */
    @TableField("file_location")
    private String fileLocation;

    /**
     * oss-桶名称
     */
    @TableField("bucket_name")
    private String bucketName;

    /**
     * oss-对象名称
     */
    @TableField("object_name")
    private String objectName;

    /**
     * oss-内容类型
     */
    @TableField("content_type")
    private String contentType;
}

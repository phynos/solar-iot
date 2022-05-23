package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文件附件表
 * </p>
 *
 * @author lupc
 * @since 2022-05-23
 */
@Getter
@Setter
@TableName("sys_file")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 文件所属业务
     */
    private String bizType;

    /**
     * 文件真实名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 文件后缀名
     */
    private String fileExtension;

    /**
     * 文件位置或URL
     */
    private String fileLocation;

    /**
     * oss-桶名称
     */
    private String bucketName;

    /**
     * oss-对象名称
     */
    private String objectName;

    /**
     * oss-内容类型
     */
    private String contentType;


}

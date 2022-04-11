package com.phynos.solar.oss;

import lombok.Data;

/**
 * 上传文件后的文件详细信息
 *
 * @author lupc
 * @date 2022/3/28 11:13
 */
@Data
public class FileInfo {

    private String bucketName;

    /**
     * 对象名称（重命名后）
     */
    private String objectName;

    /**
     * 文件实际名称
     */
    private String realName;

    /**
     * 后缀名称
     */
    private String extension;

    /**
     * 文件实际大小
     */
    private long size;

    private String contentType;

}

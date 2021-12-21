package com.phynos.solar.common.oss;

/**
 * OSS操作接口
 *
 * @author lupc
 * @date 2021/12/21 09:25
 */
public interface OssTemplate {

    OssFileInfo getObjectInfo(String bucketName, String objectName);

    String getObjectURL(String bucketName, String objectName);

}

package com.phynos.solar.common.oss;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * OSS操作接口
 *
 * @author lupc
 * @date 2021/12/21 09:25
 */
public interface OssTemplate {

    InputStream getObject(String bucketName, String objectName);

    OssFileInfo getObjectInfo(String bucketName, String objectName);

    String getObjectURL(String bucketName, String objectName);

    OssFileInfo saveObjectRealName(
            String bucketName, String dirs, MultipartFile multipartFile) throws IOException;

    OssFileInfo saveObject(
            String bucketName, String dirs, MultipartFile multipartFile) throws IOException;

    OssFileInfo saveObject(
            String bucketName, String dirs, String objectName, MultipartFile multipartFile) throws IOException;

}

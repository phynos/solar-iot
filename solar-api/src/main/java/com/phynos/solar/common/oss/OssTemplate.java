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

    /**
     * 保存对象，并使用真实文件名保存，会覆盖已经存在的对象
     *
     * @param bucketName 桶名称
     * @param dirs 保存的目录，使用 xx/xx/xxx 的形式
     * @param multipartFile
     * @return
     * @throws IOException
     */
    OssFileInfo saveObjectRealName(
            String bucketName, String dirs, MultipartFile multipartFile) throws IOException;

    OssFileInfo saveObject(
            String bucketName, String dirs, MultipartFile multipartFile) throws IOException;

    OssFileInfo saveObject(
            String bucketName, String dirs, String objectName, MultipartFile multipartFile) throws IOException;

    void removeObject(String bucketName, String objectName);

}

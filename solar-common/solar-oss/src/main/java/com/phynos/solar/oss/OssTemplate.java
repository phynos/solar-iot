package com.phynos.solar.oss;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * OSS操作接口
 *
 * @author lupc
 * @date 2022/3/28 10:58
 */
public interface OssTemplate {


    FileInfo uploadFile(String parent, MultipartFile file) throws IOException, OssException;

    /**
     * 更为通用的对象上传
     *
     * @param ossObject
     * @throws OssException
     */
    void putObject(OssObject ossObject) throws OssException;

    String getURL(String bucketName, String objectName, int expire) throws OssException;

    void download(HttpServletResponse response, String bucketName, String objectName) throws OssException;

    void removeObject(String bucketName, String objectName) throws OssException;

    byte[] getObject(String bucketName, String objectName) throws OssException;

}

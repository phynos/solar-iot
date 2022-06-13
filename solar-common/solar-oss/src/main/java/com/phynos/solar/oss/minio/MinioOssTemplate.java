package com.phynos.solar.oss.minio;

import com.phynos.solar.oss.*;
import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 基于minio实现
 *
 * @author lupc
 * @date 2022/3/29 08:51
 */
@Slf4j
public class MinioOssTemplate implements OssTemplate {

    private final OssProperties ossProperties;
    private final MinioClient minioClient;

    public MinioOssTemplate(OssProperties ossProperties, MinioClient minioClient) {
        this.ossProperties = ossProperties;
        this.minioClient = minioClient;
    }

    @Override
    public FileInfo uploadFile(String parent, MultipartFile file) throws IOException, OssException {
        String contentType = file.getContentType();
        String fileRealName = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(fileRealName);
        long size = file.getSize();
        String objectName;
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        if (StringUtils.isEmpty(extension)) {
            objectName = parent + "/" + uuid;
        } else {
            objectName = parent + "/" + uuid + "." + extension;
        }
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(ossProperties.getBucketName())
                .object(objectName)
                .stream(file.getInputStream(), size, -1)
                .contentType(contentType)
                .build();
        try {
            ObjectWriteResponse response = minioClient.putObject(args);
            log.debug("写入成功，版本号：{}", response.versionId());
        } catch (Exception e) {
            throw new OssException();
        }
        //构造返回
        FileInfo fileInfo = new FileInfo();
        fileInfo.setBucketName(ossProperties.getBucketName());
        fileInfo.setObjectName(objectName);
        fileInfo.setRealName(fileRealName);
        fileInfo.setExtension(extension);
        fileInfo.setSize(size);
        fileInfo.setContentType(contentType);
        return fileInfo;
    }

    @Override
    public void putObject(OssObject ossObject) throws OssException {
        String objectName = ossObject.getParent() + "/" + ossObject.getName();
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(ossProperties.getBucketName())
                .object(objectName)
                .stream(ossObject.getBis(), ossObject.getSize(), -1)
                .contentType(ossObject.getContentType())
                .build();
        try {
            ObjectWriteResponse response = minioClient.putObject(args);
            log.debug("写入成功，版本号：{}", response.versionId());
        } catch (Exception e) {
            throw new OssException();
        }
    }

    @Override
    public String getURL(String bucketName, String objectName, int expire) throws OssException {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .expiry(expire, TimeUnit.DAYS)
                .method(Method.GET)
                .build();
        try {
            return minioClient.getPresignedObjectUrl(args);
        } catch (Exception e) {
            throw new OssException();
        }
    }

    @Override
    public void download(HttpServletResponse httpServletResponse, String bucketName, String objectName) throws OssException {
        GetObjectArgs args = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        try (OutputStream os = httpServletResponse.getOutputStream()) {
            GetObjectResponse response = minioClient.getObject(args);
            httpServletResponse.setContentType(response.headers().get("Content-Type"));
            httpServletResponse.setContentLength(Integer.parseInt(response.headers().get("Content-Length")));
            transferTo(response, os);
        } catch (Exception e) {
            throw new OssException();
        }
    }

    @Override
    public void removeObject(String bucketName, String objectName) throws OssException {
        RemoveObjectArgs args = RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        try {
            minioClient.removeObject(args);
        } catch (Exception e) {
            throw new OssException();
        }
    }

    @Override
    public byte[] getObject(String bucketName, String objectName) throws OssException {
        GetObjectArgs args = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024 * 4);
            GetObjectResponse response = minioClient.getObject(args);
            transferTo(response, bos);
            return bos.toByteArray();
        } catch (Exception e) {
            throw new OssException();
        }
    }

    private void transferTo(GetObjectResponse response, OutputStream os) throws IOException {
        long transferred = 0;
        byte[] buffer = new byte[255];
        int read;
        while ((read = response.read(buffer, 0, 255)) >= 0) {
            os.write(buffer, 0, read);
            transferred += read;
        }
        log.debug("文件大小：{} 字节", transferred);
    }

}

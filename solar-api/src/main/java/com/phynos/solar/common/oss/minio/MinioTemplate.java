package com.phynos.solar.common.oss.minio;

import com.phynos.solar.common.oss.OssFileInfo;
import com.phynos.solar.common.oss.OssProperties;
import com.phynos.solar.common.oss.OssTemplate;
import com.phynos.solar.common.util.UuidUtil;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * minio操作模板类
 *
 * @author by lupc
 * @author lvhao
 * @date 2020-09-18 15:23
 */
public class MinioTemplate implements OssTemplate {

    private final MinioClient minioClient;

    public MinioTemplate(OssProperties ossProperties) {
        this.minioClient = MinioClient.builder()
                .endpoint(ossProperties.getUrl())
                .credentials(ossProperties.getAccessKey(), ossProperties.getSecretKey())
                .build();
    }

    /**
     * 创建存储桶
     *
     * @param bucketName 存储桶名称
     */
    public void createBucket(String bucketName) {
        boolean found = false;
        try {
            found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出所有存储桶
     *
     * @return 存储桶
     */
    public List<Bucket> getAllBuckets() throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return minioClient.listBuckets();
    }

    public Optional<Bucket> getBucket(String bucketName) {
        return null;
    }

    /**
     * 移除桶
     *
     * @param bucketName 桶名称
     */
    public void removeBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件，在项目根目录
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param fileName   下载后文件名称
     */
    public void downloadObject(String bucketName, String objectName, String fileName) {
        try {
            minioClient.downloadObject(
                    DownloadObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .filename(fileName)
                            .build());
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OssFileInfo getObjectInfo(String bucketName, String objectName) {
        StatObjectArgs args = StatObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        try {
            StatObjectResponse stat = minioClient.statObject(args);
            return OssFileInfo.fromMinioStat(stat);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件访问路径
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return URL
     */
    @Override
    public String getObjectURL(String bucketName, String objectName) {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(objectName)
                .expiry(7, TimeUnit.DAYS)
                .build();
        try {
            return minioClient.getPresignedObjectUrl(args);
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OssFileInfo saveObjectRealName(
            String bucketName, String dirs, MultipartFile multipartFile) throws IOException {
        return saveObject(bucketName, dirs, multipartFile.getOriginalFilename(), multipartFile);
    }

    @Override
    public OssFileInfo saveObject(
            String bucketName, String dirs, MultipartFile multipartFile) throws IOException {
        String uuid = UuidUtil.uid();
        String originFileName = multipartFile.getOriginalFilename();
        String fileName = uuid + "." + FilenameUtils.getExtension(originFileName);
        return saveObject(bucketName, dirs, fileName, multipartFile);
    }

    @Override
    public OssFileInfo saveObject(
            String bucketName, String dirs, String fileName, MultipartFile multipartFile) throws IOException {
        long size = multipartFile.getSize();
        String objectName = dirs + fileName;
        try (InputStream is = multipartFile.getInputStream()) {
            putObject(bucketName, objectName, is, size, multipartFile.getContentType());
            OssFileInfo ossFileInfo = new OssFileInfo();
            ossFileInfo.setFileSize(size);
            ossFileInfo.setObjectName(objectName);
            ossFileInfo.setFileRealName(multipartFile.getOriginalFilename());
            ossFileInfo.setContentType(multipartFile.getContentType());
            return ossFileInfo;
        }
    }

    /**
     * 获取对象数据
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @return
     */
    @Override
    public InputStream getObject(String bucketName, String objectName) {
        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        try {
            return minioClient.getObject(getObjectArgs);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 移除对象
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称
     */
    @Override
    public void removeObject(String bucketName, String objectName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
            e.printStackTrace();
        }
    }

    /**
     * 存储对象
     *
     * @param bucketName  桶名称
     * @param objectName  对象名称
     * @param stream      输入数据流
     * @param size        数据流大小
     * @param contextType 类型
     */
    public void putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) {
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(stream, size, -1)
                .contentType(contextType)
                .build();
        try {
            minioClient.putObject(putObjectArgs);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        }
    }

    /**
     * upload适用于比较大的文件，putObject适用于小的文件内容，upload支持自定义多线程并发上传
     *
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @param fileName   文件名称
     */
    public void uploadObject(String bucketName, String objectName, String fileName) {
        try {
            UploadObjectArgs args = UploadObjectArgs.builder()
                    .bucket(bucketName).object(objectName).filename(fileName).build();
            minioClient.uploadObject(args);
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        }
    }


}

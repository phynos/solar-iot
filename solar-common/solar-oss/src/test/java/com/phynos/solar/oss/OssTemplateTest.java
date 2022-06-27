package com.phynos.solar.oss;

import com.phynos.solar.oss.minio.MinioOssTemplate;
import io.minio.MinioClient;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class OssTemplateTest {

    static OssTemplate ossTemplate;

    @BeforeAll
    public static void initOss() {
        OssProperties ossProperties = new OssProperties();
        ossProperties.setEndpoint("http://daqishan.cn:7610");
        ossProperties.setBucketName("test");
        ossProperties.setAccessKey("phynos");
        String passwd = System.getenv().get("PASSWORD");
        ossProperties.setSecretKey(passwd);
        MinioClient minioClient = MinioClient.builder()
                .endpoint(ossProperties.getEndpoint())
                .credentials(ossProperties.getAccessKey(), ossProperties.getSecretKey())
                .build();
        ossTemplate = new MinioOssTemplate(ossProperties, minioClient);
    }

    @Test
    void getURL() throws OssException {
        String url = ossTemplate.getURL("test", "sign.png", 1);
        System.out.println(url);
    }

    @Test
    void testPutObject() throws OssException {
        String json = "{\"a\": 1}";
        byte[] buf = json.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream bis = new ByteArrayInputStream(buf);
        OssObject ossObject = OssObject.builder()
                .parent("json")
                .name(RandomUtils.nextInt(1000, 9999) + ".json")
                .contentType(ViewContentType.JSON.getType())
                .bis(bis)
                .size(buf.length)
                .build();
        ossTemplate.putObject(ossObject);
    }

}
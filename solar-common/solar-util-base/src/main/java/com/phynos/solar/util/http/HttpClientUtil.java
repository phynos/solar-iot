package com.phynos.solar.util.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * 基于apache-httpclient的封装
 *
 * @author lupc
 * @date 2024/3/4 18:14
 */
@Slf4j
public class HttpClientUtil {

    // 定义超时时间（毫秒）
    private static final int CONNECT_TIMEOUT = 5000;    // 连接超时时间
    private static final int SOCKET_TIMEOUT = 10000;   // 读取超时时间

    public static String postJson(String url, String json) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(json, "UTF-8"));

        // 创建 RequestConfig 并设置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)      // 连接超时
                .setSocketTimeout(SOCKET_TIMEOUT)        // 读取超时
                .build();
        //增加超时时间设置
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            // 获取返回的信息
            String string = EntityUtils.toString(entity, "UTF-8");
            if (response.getStatusLine().getStatusCode() == 200) {
                return string;
            } else {
                log.error("http返回失败，httpCode={}", response.getStatusLine().getStatusCode());
                log.error(string);
                throw new RuntimeException("http返回失败，httpCode=" + response.getStatusLine().getStatusCode());
            }
        }
    }

    public static String sendDataByGet(String url, Map<String, String> param) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String name : param.keySet()) {
                    builder.addParameter(name, param.get(name));
                }
            }
            HttpGet httpGet = new HttpGet(builder.build());
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    // 获取返回的信息
                    String string = EntityUtils.toString(entity, "UTF-8");
                    return string;
                }
            }
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 下载文件
     *
     * @param url
     * @param param
     * @param fileSavePath
     * @return
     * @throws IOException
     */
    public static boolean downloadFile(String url, Map<String, String> param, String fileSavePath) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String name : param.keySet()) {
                    builder.addParameter(name, param.get(name));
                }
            }
            HttpGet httpGet = new HttpGet(builder.build());
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    Header contentTypeHeader = response.getFirstHeader("Content-Type");
                    if (contentTypeHeader != null) {
                        String contentType = contentTypeHeader.getValue();
                        if (StringUtils.containsAny(contentType, "html", "text", "json")) {
                            String string = EntityUtils.toString(response.getEntity(), "UTF-8");
                            log.error(string);
                            return false;
                        }
                    }
                    try (OutputStream outputStream = Files.newOutputStream(Paths.get(fileSavePath))) {
                        response.getEntity().writeTo(outputStream);
                        log.info("File downloaded successfully!");
                        return true;
                    }
                }
            }
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 上传单个文件
     *
     * @param url
     * @param name
     * @param file
     * @return
     * @throws IOException
     */
    public static String uploadFile(String url, String name, File file) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建HttpPost请求
            HttpPost httpPost = new HttpPost(url);
            FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);
            // 创建MultipartEntityBuilder，用于构建multipart/form-data请求体
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addPart(name, fileBody); // 服务器接收文件的参数名
            // 构建HttpEntity
            HttpEntity entity = builder.build();
            // 设置请求体
            httpPost.setEntity(entity);
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    // 获取响应内容
                    HttpEntity responseEntity = response.getEntity();
                    String responseBody = EntityUtils.toString(responseEntity);
                    return responseBody;
                }
            }
        }
        return null;
    }

}

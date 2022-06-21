package com.phynos.solar.util.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * http客户端工具类
 *
 * @author lupc
 * @date 2022/6/21 15:32
 */
@Slf4j
public final class HttpUtil {

    private static final Integer connectTimeout = 5;

    private static final Integer readTimeout = 10;

    private static final Integer writeTimeout = 10;

    private static final OkHttpClient client = new OkHttpClient().newBuilder()
            // 是否开启缓存
            .retryOnConnectionFailure(false)
            //连接超时
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            //读超时
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            //写超时
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .build();

    public static String get(@NotNull String url, @NotNull Map<String, String> params) throws IOException {
        return get(url, null, params);
    }

    public static String get(@NotNull String url, Map<String, String> heads, @NotNull Map<String, String> params) throws IOException {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        params.forEach(httpBuilder::addQueryParameter);
        HttpUrl httpUrl = httpBuilder.build();
        log.info("url={}", httpUrl);
        Request.Builder requestBuild = new Request.Builder();
        if (MapUtils.isNotEmpty(heads)) {
            heads.forEach(requestBuild::addHeader);
        }
        Request request = requestBuild
                .url(httpUrl)
                .get()
                .build();
        log.debug(request.headers().toString());
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String postForm(@NotNull String url, @NotNull Map<String, String> data) {
        return null;
    }

    public static String postJson(@NotNull String url, Map<String, String> heads, @NotNull String json) throws IOException {
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public static String postMultipart(@NotNull String url) throws IOException {
        RequestBody body = new MultipartBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


}

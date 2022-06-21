package com.phynos.solar.util.http;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HttpUtilTest {

    @Test
    void get() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("username", "dfdf");
        String json = HttpUtil.get("http://baidu.com", params, params);
        System.out.println(json);
    }


}
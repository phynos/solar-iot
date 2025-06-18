package com.phynos.solar.auth.jwttoken.util;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Auth0JwtUtilTest {

    @Test
    void jwtUtilTest() {
        Map<String, String> claims = new HashMap<>();
        claims.put("aaa", "dfadfaf");
        String secret = "PDJ2544DEF4E55DF4D1";
        String token = Auth0JwtUtil.create(secret, 7, claims);
        assertNotNull(token, "token不为空");
        DecodedJWT jwt = Auth0JwtUtil.verify(secret, token);
        String decodeHeader = "";
        String decodePayload = StringUtils.toEncodedString(Base64.getDecoder().decode(jwt.getPayload()), StandardCharsets.UTF_8);

        String signature = jwt.getSignature();
        String name = jwt.getClaim("name").asString();
        String introduce = jwt.getClaim("introduce").asString();

        System.out.println("header:" + jwt.getHeader());
        System.out.println("payload:" + jwt.getPayload());
        System.out.println("signature:" + signature);

        System.out.println("headerString:" + decodeHeader);
        System.out.println("payloadString:" + decodePayload);

        System.out.println("name:" + name);
        System.out.println("introduce:" + introduce);

        String aaa = jwt.getClaim("aaa").asString();
        assertEquals(aaa, "dfadfaf");
    }

}
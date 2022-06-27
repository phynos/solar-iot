package com.phynos.solar.util.tools;

import com.phynos.solar.util.tools.UuidUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UuidUtilTest {

    @Test
    void uid() {
        String uid = UuidUtil.uid();
        System.out.println(uid);
        assertEquals(32, uid.length(), "uuid格式化后长度必须为32位");
    }

    @Test
    void xor() {
        int a = 0x01;
        int b = a << 2;
        int c = a ^ b;
        System.out.println(b);
    }

}
package com.phynos.solar.util.tools;

import java.util.UUID;

/**
 * @author lupc
 * @date 2019/9/26.
 */
public class UuidUtil {

    public static String uid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}

package com.phynos.framework.core.util;

import java.util.UUID;

/**
 * @author by Lupc
 * @date 2019/9/26.
 */
public class UuidUtil {

    public static String uid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}

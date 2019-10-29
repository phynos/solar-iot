package com.phynos.framework.activiti.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * Created by user on 2018/7/12.
 */
public class Base64Utils {

    static Logger logger = LoggerFactory.getLogger(Base64Utils.class);

    public static String ioToBase64(InputStream in) throws IOException {
        String strBase64 = null;
        try {
            byte[] bytes = new byte[in.available()];
            // 将文件中的内容读入到数组中
            in.read(bytes);
            strBase64 = Base64.getEncoder().encodeToString(bytes);
            in.close();
        } catch (IOException ioe) {
            logger.error("图片转64编码异常",ioe);
        }
        return strBase64;
    }

}

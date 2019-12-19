package com.phynos.framework.generator;

import com.phynos.framework.generator.util.FreeMarkerTemplateUtils;
import freemarker.template.Template;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: Lupc
 * @Date: 2019/12/7 9:28
 **/
public class GeneratorApplication {

    private static final String BASE_PACKAGE = "com.tf.traffic";
    private static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".casehanding.controller.sys";
    private static final String SERVICE_PACKAGE = BASE_PACKAGE + ".core.service";
    private static final String MODEL = "Role";
    private static final String diskPath = "D://";


    public static void main(String[] args) throws Exception {
        String path = diskPath + MODEL + "Controller.java";
        File file = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate("Controller.java.ftl", file, dataMap);
    }

    private static void generateFileByTemplate(final String templateName, File file, Map<String, Object> dataMap) throws Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("author", getCurrentUser());
        dataMap.put("date", getCurretnDate());
        dataMap.put("base_package", BASE_PACKAGE);
        dataMap.put("controller_package", CONTROLLER_PACKAGE);
        dataMap.put("service_package", SERVICE_PACKAGE);
        dataMap.put("model", MODEL);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
    }

    private static String getCurretnDate(){
        String result = "2019-12-19 09:10:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            result = sdf.format(new Date());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getCurrentUser(){
        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");// 获取用户名
        return userName;
    }

}

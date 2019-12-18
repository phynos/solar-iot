package com.phynos.framework.generator;

import com.phynos.framework.generator.util.FreeMarkerTemplateUtils;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: Lupc
 * @Date: 2019/12/7 9:28
 **/
public class GeneratorApplication {

    private static final String AUTHOR = "Ay";
    private static final String CURRENT_DATE = "2017/05/03";
    private static final String tableName = "tm_project_quality_problem";
    private static final String packageName = "com.evada.pm.process.manage";
    private static final String MODEL = "Notice";
    private static final String URL = "jdbc:postgresql://192.168.3.160:10655/cibpm";
    private static final String USER = "postgres";
    private static final String PASSWORD = "888888";
    private static final String DRIVER = "org.postgresql.Driver";
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
        dataMap.put("author", AUTHOR);
        dataMap.put("date", CURRENT_DATE);
        dataMap.put("package_name", packageName);
        dataMap.put("model", MODEL);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
    }

}

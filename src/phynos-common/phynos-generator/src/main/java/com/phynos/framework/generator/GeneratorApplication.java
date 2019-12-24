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

    //基础包名
    private static final String BASE_PACKAGE = "com.phynos.framework";
    //API业务
    private static final String CONTROLLER_MODULE = "web.api.controller";
    private static final String SERVICE_PACKAGE = BASE_PACKAGE + ".core.service";

    //模块
    private static final String MODEL = "Role";
    //模块中文名称
    private static final String MODEL_CHS = "角色";
    //模块分组
    private static final String MODEL_GROUP = "system";

    //主键类型
    private static final String PRIMARY_ID_TYPE = "Long";//Long,String


    //当前工程基本目录
    private static final String PROJECT_BASE_PATH = "D:\\Workspace\\Phynos-SpringBoot\\src";
    private static final String PROJECT_API_PATH = PROJECT_BASE_PATH + "\\phynos-web\\phynos-api\\src\\main\\java\\com\\phynos\\framework\\web\\api\\controller\\" + MODEL_GROUP + "\\";
    private static final String PROJECT_CORE_PATH = PROJECT_BASE_PATH + "\\phynos-common\\phynos-core\\src\\main\\java\\com\\phynos\\framework\\core\\service\\" + MODEL_GROUP + "\\";
    private static final String diskPath = "D://";


    public static void main(String[] args) throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        prepare(dataMap);

        generateFileByTemplate("Controller.java.ftl", PROJECT_API_PATH + MODEL + "Controller.java", dataMap);
        generateFileByTemplate("Service.java.ftl", PROJECT_CORE_PATH + MODEL + "Service.java", dataMap);
        generateFileByTemplate("ServiceImpl.java.ftl", PROJECT_CORE_PATH + "impl\\" + MODEL + "ServiceImpl.java", dataMap);
    }

    private static void prepare(Map<String, Object> dataMap){
        dataMap.put("author", getCurrentUser());
        dataMap.put("date", getCurretnDate());
        dataMap.put("base_package", BASE_PACKAGE);
        dataMap.put("controller_module", CONTROLLER_MODULE);
        dataMap.put("service_package", SERVICE_PACKAGE);
        dataMap.put("module_group", MODEL_GROUP);
        dataMap.put("model", MODEL);
        dataMap.put("model_chs", MODEL_CHS);
        dataMap.put("primary_id_type", PRIMARY_ID_TYPE);
    }

    private static void generateFileByTemplate(final String templateName, String path, Map<String, Object> dataMap) throws Exception {
        File file = new File(path);
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
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

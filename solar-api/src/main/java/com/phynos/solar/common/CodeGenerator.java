package com.phynos.solar.common;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.querys.PostgreSqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.PostgreSqlKeyWordsHandler;

/**
 * mybatis-plus 代码生成
 *
 * @author by lupc
 * @date 2021-01-25 9:52
 */
public class CodeGenerator {


    /**
     * 作者
     **/
    private static final String AUTHOR = "lupc";

    private static final String packageName = "com.phynos.solar.module";

    private static String tablePrefix;

    private static final String moduleName = "sys";

    private static final String[] tableNames = {
            "sys_user", "sys_role", "sys_menu",
            "sys_user_role", "sys_role_menu", "sys_role_dept", "sys_role_action",
            "sys_dict", "sys_dict_type",
            "sys_dept", "sys_area", "sys_action",
            "sys_ge_blob", "sys_ge_text",
            "sys_parameter",
            "sys_user_login_log", "sys_operation_log"};

    public static void main(String[] args) {
        //从命令行获取密码
        String password = args[0];
        FastAutoGenerator.create(dataSourceConfigBuild(password))
                // 全局配置
                .globalConfig(CodeGenerator::setGlobal)
                // 包配置
                .packageConfig(CodeGenerator::setPackage)
                // 策略配置
                .strategyConfig(CodeGenerator::setStrategy)
                // 模板配置
                .templateConfig(CodeGenerator::setTemplate)
                // 模板引擎
                .templateEngine(new VelocityTemplateEngine()) // Velocity引擎模板
                .execute();
    }

    public static final String URL = "jdbc:postgresql://www.iotroll.com:7609/iotdb?useUnicode=true&characterEncoding=utf-8";
    private static DataSourceConfig.Builder dataSourceConfigBuild(String password) {
        return new DataSourceConfig.Builder(URL, "iot", password)
                .dbQuery(new PostgreSqlQuery())
                .schema("iot")
                .typeConvert(new PostgreSqlTypeConvert() {
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, TableField tableField) {
                        String t = tableField.getType().toLowerCase();
                        if (t.contains("datetime")) {
                            return DbColumnType.DATE;
                        } else if (t.contains("bit")) {
                            return DbColumnType.BOOLEAN;
                        }
                        return super.processTypeConvert(globalConfig, tableField);
                    }
                })
                .keyWordsHandler(new PostgreSqlKeyWordsHandler());
    }

    //全局配置
    private static void setGlobal(GlobalConfig.Builder builder) {
        String projectPath = System.getProperty("user.dir") + "/solar-api/";
        String outputDir = projectPath + "/src/main/java";
        builder
                .author(AUTHOR) // 设置作者
                //.enableSwagger() // 开启 swagger 模式
                .fileOverride() // 覆盖已生成文件
                .outputDir(outputDir); // 指定输出目录
    }

    private static void setPackage(PackageConfig.Builder builder) {
        builder.parent(packageName) // 设置父包名
                .moduleName(moduleName) // 设置父包模块名
        ;
    }

    // 配置模板
    private static void setTemplate(TemplateConfig.Builder builder) {
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
//        templateConfig.setService("templates/service2.java");
//        templateConfig.setServiceImpl("templates/serviceImpl2.java");
//        templateConfig.setController("templates/controller2.java");
        // 模板引擎（如果更改了模板，则需要修改模板引擎）
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        builder.disable(TemplateType.CONTROLLER, TemplateType.XML, TemplateType.SERVICE, TemplateType.SERVICEIMPL);
    }

    // 策略配置
    private static void setStrategy(StrategyConfig.Builder builder) {
        // 策略配置
        tablePrefix = moduleName + "_";
        builder.addInclude(tableNames) // 设置需要生成的表名
                .addTablePrefix(tablePrefix, "c_") // 设置过滤表前缀
                .controllerBuilder().enableHyphenStyle().enableRestStyle()
                .entityBuilder().enableLombok()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
        ;
    }

}


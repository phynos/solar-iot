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

import java.util.HashMap;
import java.util.Map;

/**
 * mybatis-plus 代码生成
 *
 * @author by lupc
 * @date 2021-01-25 9:52
 */
public class CodeGenerator {

    private static final String projectPath = System.getProperty("user.dir") + "/solar-api/";
    /**
     * 作者
     **/
    private static final String AUTHOR = "lupc";

    private static final String packageName = "com.phynos.solar.module";

    private static final String[] tablePrefix = {"sys"};

    private static final String moduleName = "sys";

    //    private static final String[] tableNames = {
//            "sys_tenant",
//            "sys_user", "sys_role", "sys_menu", "sys_dept",
//            "sys_user_role", "sys_role_menu", "sys_role_dept",
//            "sys_dict", "sys_dict_item",
//            "sys_parameter",
//            "sys_log_login", "sys_log_audit",
//            "sys_file", "sys_file_biz"};
    private static final String[] tableNames = {
            "sys_log_login"};

    public static final String dbURL = "jdbc:postgresql://www.daqishan.cn:7609/iotdb?useUnicode=true&characterEncoding=utf-8";
    private static final String dbUsername = "iot";
    private static final String schema = "iot";

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

    private static DataSourceConfig.Builder dataSourceConfigBuild(String password) {
        return new DataSourceConfig.Builder(dbURL, dbUsername, password)
                .dbQuery(new PostgreSqlQuery())
                .schema(schema)
                .typeConvert(new PostgreSqlTypeConvert() {
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, TableField tableField) {
                        String t = tableField.getType().toLowerCase();
                        if (t.contains("bit")) {
                            return DbColumnType.BOOLEAN;
                        }
                        return super.processTypeConvert(globalConfig, tableField);
                    }
                })
                .keyWordsHandler(new PostgreSqlKeyWordsHandler());
    }

    //全局配置
    private static void setGlobal(GlobalConfig.Builder builder) {
        String outputDir = projectPath + "/src/main/java";
        builder
                .author(AUTHOR) // 设置作者
                //.enableSwagger() // 开启 swagger 模式
                .disableOpenDir()
                .outputDir(outputDir); // 指定输出目录
    }

    private static void setPackage(PackageConfig.Builder builder) {
        String xmlDir = projectPath + "/src/main/resources/mapper";
        Map<OutputFile, String> pathInfo = new HashMap<>();
        pathInfo.put(OutputFile.xml, xmlDir);
        builder.parent(packageName) // 设置父包名
                .moduleName(moduleName) // 设置父包模块名
                .pathInfo(pathInfo)
        ;
    }

    // 配置模板
    private static void setTemplate(TemplateConfig.Builder builder) {
        // 配置自定义输出模板
//        builder.entity("templates/entity2.java")
//                .service("templates/service2.java")
//                .serviceImpl("templates/serviceImpl2.java")
//                .controller("templates/controller2.java");
        builder.disable(
                TemplateType.CONTROLLER,
                TemplateType.SERVICE,
                TemplateType.SERVICEIMPL);
    }

    // 策略配置
    private static void setStrategy(StrategyConfig.Builder builder) {
        // 策略配置
        builder.addInclude(tableNames) // 设置需要生成的表名
                .addTablePrefix(tablePrefix) // 设置过滤表前缀
                //.enableSchema()
                .controllerBuilder()
                .enableHyphenStyle()
                .enableRestStyle()
                .entityBuilder()
                .fileOverride()
                .enableLombok()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .mapperBuilder()
                .enableBaseColumnList()
                .enableBaseResultMap()
        ;
    }

}


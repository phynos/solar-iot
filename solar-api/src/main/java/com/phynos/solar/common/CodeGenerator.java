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
 * @author lupc
 * @date 2021-01-25 9:52
 */
public class CodeGenerator {

    private static final String projectPath = System.getProperty("user.dir") + "/solar-api/";
    /**
     * 作者
     **/
    private static final String AUTHOR = "lupc";

    public static final String dbURL = "jdbc:postgresql://daqishan.cn:5432/iotdb?currentSchema=iotdb";
    private static final String dbUsername = "postgres";
    private static final String schema = "iot";

    private static final String packageName = "com.phynos.solar.module";

    //private static final String[] tablePrefix = {"sys", "ten"};
    private static final String[] tablePrefix = {};

    private static final String moduleName = "sys";
    private static final String[] tableNames = {
            "sys_tenant", "sys_tenant_config",
            "sys_config",
            "sys_dict", "sys_dict_item",
            "sys_log_login", "sys_log_audit",
            "sys_file", "sys_file_biz"
    };
//    private static final String moduleName = "ten";
//    private static final String[] tableNames = {
//            "ten_user", "ten_role", "ten_menu", "ten_dept", "ten_post",
//            "ten_user_role", "ten_user_post",
//            "ten_role_menu", "ten_role_dept",
//            "ten_dept_config"
//    };

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

    // 策略配置
    private static void setStrategy(StrategyConfig.Builder builder) {
        // 策略配置
        builder.addInclude(tableNames) // 设置需要生成的表名
                .addTablePrefix(tablePrefix) // 设置过滤表前缀
                //.enableSchema()
                .controllerBuilder()
                .disable()
//                .enableHyphenStyle()
//                .enableRestStyle()
                .entityBuilder()
                .enableFileOverride()
                .enableLombok()
                .enableTableFieldAnnotation()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .mapperBuilder()
                .enableFileOverride()
                .enableBaseColumnList()
                .enableBaseResultMap()
                .serviceBuilder()
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImpl")
        ;
    }

}


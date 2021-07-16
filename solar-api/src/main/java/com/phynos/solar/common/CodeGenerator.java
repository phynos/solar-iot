package com.phynos.solar.common;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

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
            "sys_user_role", "sys_role_menu",
            "sys_dict", "sys_dict_type",
            "sys_operation_log"};

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        //从命令行获取密码
        String password = args[0];
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        setGlobalConfig(mpg);
        // 数据源配置
        setDataSource(mpg, password);
        // 包配置
        setPackageConfig(mpg);
        // 自定义配置
        setInjectionConfig(mpg);
        // 配置模板
        setTemplate(mpg);
        // 策略配置
        tablePrefix = moduleName + "_";
        setStrategy(mpg);
        mpg.execute();
    }

    private static void setGlobalConfig(AutoGenerator mpg) {
        String projectPath = System.getProperty("user.dir") + "/solar-api/";
        GlobalConfig gc = new GlobalConfig()
                .setOutputDir(projectPath + "/src/main/java")
                .setAuthor(AUTHOR)
                .setFileOverride(true)
                .setOpen(false);
        mpg.setGlobalConfig(gc);
    }

    private static void setPackageConfig(AutoGenerator mpg) {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(packageName);
        mpg.setPackageInfo(pc);
    }

    // 数据源配置
    private static void setDataSource(AutoGenerator mpg, String password) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://www.iotroll.com:7605/charger?useUnicode=true&characterEncoding=utf-8");
        //dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword(password);
        dsc.setTypeConvert(new OracleTypeConvert() {
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
        });
        mpg.setDataSource(dsc);
    }

    // 配置模板
    private static void setTemplate(AutoGenerator mpg) {
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
//        templateConfig.setService("templates/service2.java");
//        templateConfig.setServiceImpl("templates/serviceImpl2.java");
//        templateConfig.setController("templates/controller2.java");
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setController(null);
        templateConfig.setXml(null);
        // 模板引擎（如果更改了模板，则需要修改模板引擎）
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.setTemplate(templateConfig);
    }

    // 策略配置
    private static void setStrategy(AutoGenerator mpg) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                //.setSuperEntityClass("你自己的父类实体,没有就不用设置!") //公共父类
                //.setSuperEntityColumns("id") //写于父类中的公共字段
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setTablePrefix(tablePrefix)
                .setInclude(tableNames)
                .setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
    }

    //自定义配置
    private static void setInjectionConfig(AutoGenerator mpg) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
//        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }

}


package com.phynos.ds.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2025/6/18 17:30
 */
@MapperScan(
        basePackages = "com.phynos.ds.mapper",
        sqlSessionFactoryRef = "sqlSessionFactory")
@Configuration
public class MybatisPlusConfig {

    @Bean
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource dataSource = new DynamicDataSource();
        return dataSource;
    }

    //配置事务管理器
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        //添加xml映射路径
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource[] resources = resolver.getResources("classpath:mapper/*.xml");
//        factory.setMapperLocations(resources);
        //添加变量配置
        Properties properties = new Properties();
        properties.setProperty("tag", "gsSqlSessionFactory");
        factory.setConfigurationProperties(properties);
        //相关插件必须在这里设置，不能使用自动注入
        factory.setPlugins(mybatisPlusInterceptor());
        //自定义设置
        //factory.setGlobalConfig(globalConfig(gsPrefix));
        return factory.getObject();
    }

    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //乐观锁插件
        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.ORACLE));
        return interceptor;
    }

    public GlobalConfig globalConfig(String scheme) {
        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setSchema(scheme);
        globalConfig.setDbConfig(dbConfig);
        return globalConfig;
    }

}

package com.phynos.ds.controller;

import com.phynos.ds.mapper.UserMapper;
import com.phynos.ds.model.User;
import com.phynos.ds.mybatisplus.DynamicDataSource;
import com.phynos.ds.mybatisplus.DynamicDataSourceContextHolder;
import com.phynos.ds.provider.DataSourceProperties;
import com.phynos.ds.provider.DataSourceProvider;
import com.phynos.ds.provider.DataSourceUtil;
import com.phynos.ds.provider.impl.DefaultDataSourceProvider;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2025/6/18 18:00
 */
@Slf4j
@Controller
public class DsTestController {

    @Resource
    UserMapper userMapper;
    @Autowired
    DynamicDataSource dynamicDataSource;

    @PostConstruct
    public void init() {
        log.info("启动了");
        test4();
    }

    private void test1() {
        addDataSource();
        DynamicDataSourceContextHolder.push("a");
        List<User> a = userMapper.listAll();
        log.info(a.toString());
        DynamicDataSourceContextHolder.push("b");
        List<User> b = userMapper.listAll();
        log.info(b.toString());
    }

    private void test2() {
        DataSourceProperties prop = new DataSourceProperties();
        prop.setPoolName("db-a");
        prop.setDriverClassName("org.sqlite.JDBC");
        prop.setJdbcUrl("jdbc:sqlite:./files/a.db");
        prop.setUsername("a");
        prop.setPassword("123456");
        DataSource dataSource = DataSourceUtil.createHikariDataSource(prop);
        testDataSourceConfig(dataSource);
    }

    private void test3() {
        DataSourceProperties prop = new DataSourceProperties();
        prop.setPoolName("db-a");
        prop.setDriverClassName("org.sqlite.JDBC");
        prop.setJdbcUrl("jdbc:sqlite:./files/a.db");
        prop.setUsername("a");
        prop.setPassword("123456");
        DataSource dataSource = DataSourceUtil.createHikariDataSource(prop);
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user");
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                log.info("id={} name={}", id, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void test4() {
        DataSourceProperties prop = new DataSourceProperties();
        prop.setPoolName("db-q");
        prop.setDriverClassName("org.postgresql.Driver");
        prop.setJdbcUrl("jdbc:postgresql://124.221.51.95:39002/postgres?currentSchema=public");
        prop.setUsername("postgres");
        prop.setPassword("123456");
        DataSource dataSource = DataSourceUtil.createHikariDataSource(prop);
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user");
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                log.info("id={} name={}", id, name);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void addDataSource() {
        //基于本地h2数据库创建
        Map<String, DataSourceProperties> dsMap = new HashMap<>();
        {
            DataSourceProperties prop = new DataSourceProperties();
            prop.setPoolName("db-a");
            prop.setDriverClassName("org.sqlite.JDBC");
            prop.setJdbcUrl("jdbc:sqlite:./files/a.db");
            prop.setUsername("a");
            prop.setPassword("123456");
            dsMap.put("a", prop);
        }
        {
            DataSourceProperties prop = new DataSourceProperties();
            prop.setPoolName("db-b");
            prop.setDriverClassName("org.sqlite.JDBC");
            prop.setJdbcUrl("jdbc:sqlite:./files/b.db");
            prop.setUsername("b");
            prop.setPassword("123456");
            dsMap.put("b", prop);
        }
        DataSourceProvider provider = new DefaultDataSourceProvider(dsMap);
        provider.loadDataSources().forEach((k, v) -> dynamicDataSource.addDataSource(k, v));
    }

    private void testDataSourceConfig(DataSource dataSource) {
        SqlSessionFactory sqlSessionFactory = createSqlSessionFactory(dataSource);
        try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> a = userMapper.listAll();
            log.info(a.toString());
        }
    }

    public static SqlSessionFactory createSqlSessionFactory(DataSource dataSource) {
        // 1. 创建事务工厂（JDBC 事务管理）
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        // 2. 创建 MyBatis 环境（绑定 DataSource 和 TransactionFactory）
        Environment environment = new Environment("test", transactionFactory, dataSource);

        // 3. 创建 MyBatis 配置（可在此添加 Mapper 扫描等配置）
        Configuration configuration = new Configuration(environment);
        // 可选：添加 Mapper 接口或 XML 映射文件
        configuration.addMapper(UserMapper.class);
        // configuration.addMappers("com.example.mapper");

        // 4. 通过 SqlSessionFactoryBuilder 构建 SqlSessionFactory
        return new SqlSessionFactoryBuilder().build(configuration);
    }

}

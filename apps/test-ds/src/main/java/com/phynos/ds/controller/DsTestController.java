package com.phynos.ds.controller;

import com.phynos.ds.mapper.UserMapper;
import com.phynos.ds.model.User;
import com.phynos.ds.mybatisplus.DynamicDataSource;
import com.phynos.ds.mybatisplus.DynamicDataSourceContextHolder;
import com.phynos.ds.provider.DataSourceProperties;
import com.phynos.ds.provider.DataSourceProvider;
import com.phynos.ds.provider.impl.DefaultDataSourceProvider;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
        addDataSource();
        DynamicDataSourceContextHolder.push("a");
        List<User> a = userMapper.listAll();
        log.info(a.toString());
        DynamicDataSourceContextHolder.push("b");
        List<User> b = userMapper.listAll();
        log.info(b.toString());
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

}

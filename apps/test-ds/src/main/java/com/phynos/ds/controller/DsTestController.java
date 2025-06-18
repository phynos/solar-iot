package com.phynos.ds.controller;

import com.phynos.ds.mapper.UserMapper;
import com.phynos.ds.mybatisplus.DynamicDataSource;
import com.phynos.ds.provider.DataSourceProperties;
import com.phynos.ds.provider.DataSourceProvider;
import com.phynos.ds.provider.impl.DefaultDataSourceProvider;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
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
        userMapper.listAll();
    }


    private void addDataSource() {
        //基于本地h2数据库创建
        Map<String, DataSourceProperties> dsMap = new HashMap<>();
        {
            DataSourceProperties prop = new DataSourceProperties();
            prop.setDriverClassName("org.h2.Driver");
            prop.setJdbcUrl("jdbc:h2:file:files/a.db;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE");
            prop.setUsername("sa");
            dsMap.put("a", prop);
        }
        {
            DataSourceProperties prop = new DataSourceProperties();
            prop.setDriverClassName("org.h2.Driver");
            prop.setJdbcUrl("jdbc:h2:file:files/b.db;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE");
            prop.setUsername("sa");
            dsMap.put("b", prop);
        }
        DataSourceProvider provider = new DefaultDataSourceProvider(dsMap);
        provider.loadDataSources().forEach( (k,v) -> dynamicDataSource.addDataSource(k, v));
    }

}

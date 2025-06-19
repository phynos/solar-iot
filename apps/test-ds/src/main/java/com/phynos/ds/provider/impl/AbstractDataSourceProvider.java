package com.phynos.ds.provider.impl;

import com.phynos.ds.provider.DataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2025/6/18 19:11
 */
public abstract class AbstractDataSourceProvider {

    protected Map<String, DataSource> createDataSourceMap(
            Map<String, DataSourceProperties> dataSourcePropertiesMap) {
        Map<String, DataSource> dataSourceMap = new HashMap<>(dataSourcePropertiesMap.size() * 2);
        for (Map.Entry<String, DataSourceProperties> item : dataSourcePropertiesMap.entrySet()) {
            String dsName = item.getKey();
            DataSourceProperties dataSourceProperty = item.getValue();
            String poolName = dataSourceProperty.getPoolName();
            if (poolName == null || "".equals(poolName)) {
                poolName = dsName;
            }
            dataSourceProperty.setPoolName(poolName);
            dataSourceMap.put(dsName, createHikariDataSource(dataSourceProperty));
        }
        return dataSourceMap;
    }

    private DataSource createDataSource(DataSourceProperties prop) {
        return DataSourceBuilder.create()
                .driverClassName(prop.getDriverClassName())
                .username(prop.getUsername())
                .password(prop.getPassword())
                .url(prop.getJdbcUrl())
                .type(HikariDataSource.class)
                .build();
    }

    private HikariDataSource createHikariDataSource(DataSourceProperties prop) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(prop.getDriverClassName());
        dataSource.setUsername(prop.getUsername());
        dataSource.setPassword(prop.getPassword());
        dataSource.setJdbcUrl(prop.getJdbcUrl());

        // 手动配置 HikariCP 参数
        dataSource.setConnectionTimeout(30000);
        dataSource.setMaximumPoolSize(20);
        dataSource.setMinimumIdle(5);
        dataSource.setIdleTimeout(600000);
        dataSource.setMaxLifetime(1800000);
        dataSource.setPoolName(prop.getPoolName());

        return dataSource;
    }

}

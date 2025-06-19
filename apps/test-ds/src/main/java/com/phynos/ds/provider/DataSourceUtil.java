package com.phynos.ds.provider;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2025/6/19 09:25
 */
public class DataSourceUtil {
    private DataSource createDataSource(DataSourceProperties prop) {
        return DataSourceBuilder.create()
                .driverClassName(prop.getDriverClassName())
                .username(prop.getUsername())
                .password(prop.getPassword())
                .url(prop.getJdbcUrl())
                .type(HikariDataSource.class)
                .build();
    }

    public static HikariDataSource createHikariDataSource(DataSourceProperties prop) {
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

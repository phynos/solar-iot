package com.phynos.ds.provider.impl;

import com.phynos.ds.provider.DataSourceProperties;
import com.phynos.ds.provider.DataSourceUtil;
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
            dataSourceMap.put(dsName, DataSourceUtil.createHikariDataSource(dataSourceProperty));
        }
        return dataSourceMap;
    }



}

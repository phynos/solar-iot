package com.phynos.ds.mybatisplus;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2025/6/18 17:30
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private final Map<Object, Object> dataSources = new ConcurrentHashMap<>();

    public  DynamicDataSource() {
        super.setTargetDataSources(dataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dsKey = DynamicDataSourceContextHolder.peek();
        return dsKey;
    }

    public synchronized void addDataSource(String ds, DataSource dataSource) {
        //这里如何处理
        dataSources.put(ds, dataSource);
        super.setTargetDataSources(dataSources);
        super.afterPropertiesSet();
    }

}

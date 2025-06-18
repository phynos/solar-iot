package com.phynos.ds.provider.impl;

import com.phynos.ds.provider.DataSourceProperties;
import com.phynos.ds.provider.DataSourceProvider;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2025/6/18 18:58
 */
public class DefaultDataSourceProvider extends AbstractDataSourceProvider implements DataSourceProvider {

    private final Map<String, DataSourceProperties> dataSourcePropertiesMap;

    public DefaultDataSourceProvider(Map<String, DataSourceProperties> dataSourcePropertiesMap) {
        this.dataSourcePropertiesMap = dataSourcePropertiesMap;
    }

    @Override
    public Map<String, DataSource> loadDataSources() {
        return createDataSourceMap(dataSourcePropertiesMap);
    }

}

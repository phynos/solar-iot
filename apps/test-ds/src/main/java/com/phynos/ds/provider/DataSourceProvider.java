package com.phynos.ds.provider;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2025/6/18 18:57
 */
public interface DataSourceProvider {

    Map<String, DataSource> loadDataSources();

}

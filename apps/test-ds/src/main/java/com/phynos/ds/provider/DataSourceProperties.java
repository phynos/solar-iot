package com.phynos.ds.provider;

import lombok.Data;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2025/6/18 18:58
 */
@Data
public class DataSourceProperties {

    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;
    private String schema;

    private String poolName;

}

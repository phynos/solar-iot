package com.phynos.solar.common.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.phynos.solar.auth.TenantConext;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 多租户处理
 *
 * @author lupc
 * @date 2022/5/23 14:25
 */
public class TenantHandler implements TenantLineHandler {

    private final String[] ignoreTables = {
            "sys_dict", "sys_dict_item"
    };

    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }

    @Override
    public boolean ignoreTable(String tableName) {
        boolean ignore = ArrayUtils.contains(ignoreTables, tableName);
        return ignore;
    }

    @Override
    public Expression getTenantId() {
        return new LongValue(TenantConext.getTenantId());
    }


}

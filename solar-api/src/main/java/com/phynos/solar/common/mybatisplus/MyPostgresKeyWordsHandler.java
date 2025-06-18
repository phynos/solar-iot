package com.phynos.solar.common.mybatisplus;

import com.baomidou.mybatisplus.generator.keywords.PostgreSqlKeyWordsHandler;

/**
 * 间接通过关键字来为字段增加引号
 *
 * @author lupc
 * @date 2023/7/26 08:56
 */
public class MyPostgresKeyWordsHandler extends PostgreSqlKeyWordsHandler {

    @Override
    public boolean isKeyWords(String columnName) {
        return true;
    }

}

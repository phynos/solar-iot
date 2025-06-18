package com.phynos.solar.auth;

import com.phynos.solar.util.json.ResultCodeEnum;
import lombok.Getter;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/4/26 09:05
 */
@Getter
public class AuthException extends Exception {

    private final ResultCodeEnum resultCodeEnum;

    public AuthException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
    }

}

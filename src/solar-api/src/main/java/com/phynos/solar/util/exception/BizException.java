package com.phynos.solar.util.exception;

import com.phynos.solar.util.json.R;
import lombok.Getter;

/**
 * 通用业务运行时异常
 *
 * @author by lupc
 * @date 2021-02-24 17:28
 */
@Getter
public class BizException extends RuntimeException {

    private R<?> r;

    public BizException(R<?> r) {
        this.r = r;
    }

}

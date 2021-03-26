package com.phynos.solar.module.index.controller;

import com.phynos.solar.common.dto.BasePageDTO;
import com.phynos.solar.common.exception.BizException;
import com.phynos.solar.common.util.json.R;
import com.phynos.solar.common.util.json.ResultCodeEnum;
import com.phynos.solar.module.index.dto.ExampleDTO;
import com.phynos.solar.module.index.service.DebugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lupc
 * @date 2021/3/26 13:57
 */
@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @Autowired
    DebugService debugService;

    @GetMapping("/dto")
    public R<?> test(@Valid ExampleDTO dto) {
        return debugService.test();
    }

    @GetMapping("/page")
    public R<?> page(@Valid BasePageDTO dto) {
        return debugService.test();
    }

    @GetMapping("/error")
    public R<?> error() {
        throw new BizException(R.msg(ResultCodeEnum.UNSURPORT, "自定义业务异常"));
    }

}

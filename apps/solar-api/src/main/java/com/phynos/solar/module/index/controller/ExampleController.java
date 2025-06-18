package com.phynos.solar.module.index.controller;

import com.phynos.solar.common.dto.BasePageDTO;
import com.phynos.solar.module.index.dto.DateDTO;
import com.phynos.solar.module.index.dto.LocalDateDTO;
import com.phynos.solar.util.web.exception.BizException;
import com.phynos.solar.module.index.dto.ExampleDTO;
import com.phynos.solar.module.index.service.DebugService;
import com.phynos.solar.util.json.R;
import com.phynos.solar.util.json.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * @author lupc
 * @date 2021/3/26 13:57
 */
@RestController
@RequestMapping("/example")
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
    public R<?> error() throws BizException {
        throw new BizException(R.msg(ResultCodeEnum.UNSURPORT, "自定义业务异常"));
    }

    @GetMapping("/date")
    public R<?> date(@Valid DateDTO dto) {
        return R.data(dto);
    }

    @GetMapping("/localDate")
    public R<?> localDate(@Valid LocalDateDTO dto) {
        return R.data(dto);
    }

}

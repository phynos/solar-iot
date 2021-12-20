package com.phynos.solar.module.sys.service.impl;

import com.phynos.solar.common.util.UuidUtil;
import com.phynos.solar.module.sys.entity.Parameter;
import com.phynos.solar.module.sys.mapper.ParameterMapper;
import com.phynos.solar.module.sys.service.ParameterService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2021/12/20 17:02
 */
@Service
public class ParameterServiceImpl implements ParameterService {

    @Resource
    ParameterMapper parameterMapper;

    @PostConstruct
    public void init() {
        Parameter entity = new Parameter();
        entity.setCreatedDatetime(LocalDateTime.now());
        entity.setCreatedUserId(1L);
        entity.setParaType(1);
        entity.setParaKey(UuidUtil.uid());
        entity.setParaLabel("1");
        entity.setParaValue("1");
        parameterMapper.insert(entity);
    }

}

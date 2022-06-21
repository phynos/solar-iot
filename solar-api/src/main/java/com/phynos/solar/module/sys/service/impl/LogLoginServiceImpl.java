package com.phynos.solar.module.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.phynos.solar.module.sys.entity.LogLogin;
import com.phynos.solar.module.sys.mapper.LogLoginMapper;
import com.phynos.solar.module.sys.service.LogLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/6/21 09:57
 */
@Service
public class LogLoginServiceImpl implements LogLoginService {

    @Resource
    LogLoginMapper logLoginMapper;

    @Profile("lupc")
    @PostConstruct
    public void testData() {
        String[] ips = new String[]{"172.16.22.89", "192.168.0.1"};
        List<LogLogin> data = new ArrayList<>();

        for (int i = 1; i <= 100000; i++) {
            LogLogin entity = new LogLogin();
            entity.setId(IdWorker.getId());
            entity.setUserId(1L);
            entity.setLoginIp(StringUtils.join(ips, ","));
            entity.setPlatform(1);
            data.add(entity);
            if (i % 2000 == 0) {
                logLoginMapper.batchInsert(data);
                data.clear();
            }
        }
    }

    @Override
    public void insertLog(long userId, String[] ips, int platform) {
        LogLogin entity = new LogLogin();
        entity.setUserId(userId);
        entity.setLoginIp(StringUtils.join(ips, ","));
        entity.setPlatform(platform);
        logLoginMapper.insert(entity);
    }

}

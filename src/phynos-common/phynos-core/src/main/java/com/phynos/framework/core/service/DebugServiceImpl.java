package com.phynos.framework.core.service;

import com.phynos.framework.core.service.DebugService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @Author: Lupc
 * @Date: 2019/11/27 14:39
 **/
@Service
public class DebugServiceImpl implements DebugService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    Environment env;

    @Override
    public String getActiveProfiles() {
        String[] profiles = env.getActiveProfiles();
        String profile = StringUtils.join(profiles, ",");
        logger.debug(profile);
        return profile;
    }

}

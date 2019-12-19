package ${base_package}.core.service.impl;

import ${base_package}.core.service.DebugService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
* @Author: ${author}
* @Date: ${date}
**/
@Service
public class ${model}ServiceImpl implements  ${model}Service {

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

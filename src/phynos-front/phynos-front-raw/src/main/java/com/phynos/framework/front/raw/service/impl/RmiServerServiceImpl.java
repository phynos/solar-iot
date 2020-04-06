package com.phynos.framework.front.raw.service.impl;

import com.phynos.framework.front.raw.service.RmiServerService;
import org.springframework.stereotype.Service;

/**
 * @author by lupc
 * @date 2020-04-06 9:37
 */
@Service
public class RmiServerServiceImpl implements RmiServerService {

    @Override
    public String sayHello(String msg) {
        return "I am server,you said:" +msg;
    }

}

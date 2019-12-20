package com.phynos.framework.web.api.controller;

import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.json.ResultCodeEnum;
import com.phynos.framework.push.rabbitmq.RabbitProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by Lupc
 * @date 2019/9/26.
 */
@RestController
@RequestMapping("/api")
public class IndexController {


    @Autowired
    RabbitProduct rabbitProduct;

    @RequestMapping("/test/push")
    public JsonResult pushTest(String msg){
        rabbitProduct.sendMSG(msg);
        return JsonResult.code(ResultCodeEnum.OK);
    }

}

package com.phynos.framework.web.api.controller.system;

import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.web.api.controller.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Lupc
 * @Date: 2019/10/31 9:27
 **/
@RestController
@RequestMapping("/api/sys/user")
public class UserController extends BaseController {

    @PostMapping("/profile")
    public JsonResult profile(){
        return JsonResult.ok();
    }


}

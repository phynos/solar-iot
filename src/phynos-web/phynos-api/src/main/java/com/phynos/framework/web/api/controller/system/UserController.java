package com.phynos.framework.web.api.controller.system;

import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.params.BaseParam;
import com.phynos.framework.core.params.IdParam;
import com.phynos.framework.core.service.system.UserService;
import com.phynos.framework.dao.model.User;
import com.phynos.framework.web.api.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    UserService userService;

    @PostMapping("/list")
    public JsonResult list(BaseParam param) {
        return userService.list(param);
    }

    @PostMapping("/add")
    public JsonResult add(@RequestBody User param) {
        return userService.add(param);
    }

    @PostMapping("/del")
    public JsonResult del(@RequestBody List<Long> ids) {
        return userService.del(ids);
    }

    @PostMapping("/mod")
    public JsonResult mod(@RequestBody User param) {
        return userService.mod(param);
    }

    @PostMapping("/info")
    public JsonResult info(@RequestBody IdParam<Long> param) {
        return userService.info(param.getId());
    }


}

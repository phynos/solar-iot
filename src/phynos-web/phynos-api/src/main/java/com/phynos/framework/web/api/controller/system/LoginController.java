package com.phynos.framework.web.api.controller.system;

import com.phynos.framework.core.json.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Lupc
 * @Date: 2019/10/31 9:26
 **/
@RestController
@RequestMapping("/api")
public class LoginController {

    @GetMapping("/login")
    public JsonResult loginGet(){
        return JsonResult.ok();
    }

    @PostMapping("/login")
    public JsonResult loginPost(){
        return JsonResult.ok();
    }

    @GetMapping("/logout")
    @PostMapping("/login")
    public JsonResult logout(){
        return JsonResult.ok();
    }


}

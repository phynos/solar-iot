package com.phynos.solar.module.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phynos.solar.module.sys.entity.User;
import com.phynos.solar.module.sys.service.UserService;
import com.phynos.solar.util.json.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 管理用户表格 前端控制器
 * </p>
 *
 * @author lupc
 * @since 2021-02-23
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/pageList")
    public R<?> pageList() {
        Page<User> userList = userService.pageList();
        return R.data(userList);
    }


}


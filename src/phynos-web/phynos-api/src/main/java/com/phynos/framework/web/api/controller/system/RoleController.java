package com.phynos.framework.web.api.controller.system;

import com.phynos.framework.core.annotation.OperationRecord;
import com.phynos.framework.core.constant.BusinessType;

import com.phynos.framework.web.api.controller.BaseController;

import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.params.BaseParam;
import com.phynos.framework.core.params.IdParam;
import com.phynos.framework.core.service.RoleService;
import com.phynos.framework.dao.model.Role;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @Author: Lupc
* @Date: 2019-12-19 23:47:21
**/
@RestController
@RequestMapping("/api/sys/role")
public class RoleController extends BaseController {

    @Autowired
    RoleService roleService;

    @PostMapping("/list")
    public JsonResult list(BaseParam param) {
        return roleService.list(param);
    }

    @PostMapping("/add")
    public JsonResult add(@RequestBody Role param) {
        return roleService.add(param);
    }

    @PostMapping("/del")
    public JsonResult del(@RequestBody List<Long> ids) {
        return roleService.del(ids);
    }

    @PostMapping("/mod")
    public JsonResult mod(@RequestBody Role param) {
        return roleService.mod(param);
    }

    @PostMapping("/info")
    public JsonResult info(@RequestBody IdParam param) {
        return roleService.info(param.getId());
    }

}
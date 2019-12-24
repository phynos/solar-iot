package com.phynos.framework.web.api.controller.system;

import com.phynos.framework.core.annotation.OperationRecord;
import com.phynos.framework.core.constant.BusinessType;

import com.phynos.framework.web.api.controller.BaseController;

import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.params.BaseParam;
import com.phynos.framework.core.params.IdParam;
import com.phynos.framework.core.service.system.RoleService;
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
* @Author: 1
* @Date: 2019-12-24 10:23:41
**/
@RestController
@RequestMapping("/api/system/role")
@Api(value="角色管理",tags={"角色管理"})
public class RoleController extends BaseController {

    @Autowired
    RoleService roleService;

    @PostMapping("/list")
    public JsonResult list(BaseParam param) {
        return roleService.list(param);
    }

    @PostMapping("/add")
    @OperationRecord(module = "角色管理",action = BusinessType.INSERT)
    @ApiOperation(value = "角色新增",notes = "角色新增")
    public JsonResult add(@RequestBody Role param) {
        return roleService.add(param);
    }

    @PostMapping("/del")
    @OperationRecord(module = "角色管理",action = BusinessType.DELETE)
    @ApiOperation(value = "角色删除",notes = "角色删除")
    public JsonResult del(@RequestBody List<Long> ids) {
        return roleService.del(ids);
    }

    @PostMapping("/mod")
    @OperationRecord(module = "角色管理",action = BusinessType.UPDATE)
    @ApiOperation(value = "角色修改",notes = "角色修改")
    public JsonResult mod(@RequestBody Role param) {
        return roleService.mod(param);
    }

    @PostMapping("/info")
    public JsonResult info(@RequestBody IdParam<Long> param) {
        return roleService.info(param.getId());
    }

}
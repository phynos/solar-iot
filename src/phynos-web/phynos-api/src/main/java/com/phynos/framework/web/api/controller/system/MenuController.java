package com.phynos.framework.web.api.controller.system;

import com.phynos.framework.core.annotation.OperationRecord;
import com.phynos.framework.core.constant.BusinessType;

import com.phynos.framework.web.api.controller.BaseController;

import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.params.BaseParam;
import com.phynos.framework.core.params.IdParam;
import com.phynos.framework.core.service.system.MenuService;
import com.phynos.framework.dao.model.Menu;

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
* @Date: 2019-12-24 10:28:03
**/
@RestController
@RequestMapping("/api/system/menu")
@Api(value="菜单管理",tags={"菜单管理"})
public class MenuController extends BaseController {

    @Autowired
    MenuService menuService;

    @PostMapping("/list")
    public JsonResult list(BaseParam param) {
        return menuService.list(param);
    }

    @PostMapping("/add")
    @OperationRecord(module = "菜单管理",action = BusinessType.INSERT)
    @ApiOperation(value = "菜单新增",notes = "菜单新增")
    public JsonResult add(@RequestBody Menu param) {
        return menuService.add(param);
    }

    @PostMapping("/del")
    @OperationRecord(module = "菜单管理",action = BusinessType.DELETE)
    @ApiOperation(value = "菜单删除",notes = "菜单删除")
    public JsonResult del(@RequestBody List<Long> ids) {
        return menuService.del(ids);
    }

    @PostMapping("/mod")
    @OperationRecord(module = "菜单管理",action = BusinessType.UPDATE)
    @ApiOperation(value = "菜单修改",notes = "菜单修改")
    public JsonResult mod(@RequestBody Menu param) {
        return menuService.mod(param);
    }

    @PostMapping("/info")
    public JsonResult info(@RequestBody IdParam<Long> param) {
        return menuService.info(param.getId());
    }

}
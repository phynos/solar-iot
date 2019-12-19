package com.phynos.framework.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.phynos.framework.core.json.JsonList;
import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.params.BaseParam;
import com.phynos.framework.core.service.RoleService;
import com.phynos.framework.dao.mapper.RoleMapper;
import com.phynos.framework.dao.model.Role;
import com.phynos.framework.dao.model.RoleExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @Author: Lupc
* @Date: 2019-12-19 23:40:31
**/
@Service
public class RoleServiceImpl  extends BaseServiceImpl implements RoleService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    RoleMapper roleMapper;

    @Override
    public JsonResult list(BaseParam param) {
        Page page = PageHelper.startPage(param.getPageIndex(), param.getPageSize());
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        List<Role> data = roleMapper.selectByExample(roleExample);
        long total = page.getTotal();
        PageHelper.clearPage();//一定要清理
        JsonList jl = JsonList.create(total, param.getPageIndex(), param.getPageSize(), data);
        return JsonResult.data(jl);
    }

    @Override
    public JsonResult add(Role param) {
        roleMapper.insertSelective(param);
        return OK;
    }

    @Override
    public JsonResult del(List<Long> ids) {
        ids.forEach(id -> roleMapper.deleteByPrimaryKey(id));
        return OK;
    }

    @Override
    public JsonResult mod(Role param) {
        roleMapper.updateByPrimaryKey(param);
        return OK;
    }

    @Override
    public JsonResult info(Long id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        return JsonResult.data(role);
    }

}

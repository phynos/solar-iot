package com.phynos.framework.core.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.phynos.framework.core.json.JsonList;
import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.params.BaseParam;
import com.phynos.framework.core.service.BaseServiceImpl;
import com.phynos.framework.core.service.system.MenuService;
import com.phynos.framework.dao.mapper.MenuMapper;
import com.phynos.framework.dao.model.Menu;
import com.phynos.framework.dao.model.MenuExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @Author: 1
* @Date: 2019-12-24 10:28:03
**/
@Service
public class MenuServiceImpl  extends BaseServiceImpl implements MenuService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    MenuMapper menuMapper;

    @Override
    public JsonResult list(BaseParam param) {
        Page page = PageHelper.startPage(param.getPageIndex(), param.getPageSize());
        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        List<Menu> data = menuMapper.selectByExample(menuExample);
        long total = page.getTotal();
        PageHelper.clearPage();//一定要清理
        JsonList jl = JsonList.create(total, param.getPageIndex(), param.getPageSize(), data);
        return JsonResult.data(jl);
    }

    @Override
    public JsonResult add(Menu param) {
        menuMapper.insertSelective(param);
        return OK;
    }

    @Override
    public JsonResult del(List<Long> ids) {
        ids.forEach(id -> menuMapper.deleteByPrimaryKey(id));
        return OK;
    }

    @Override
    public JsonResult mod(Menu param) {
        menuMapper.updateByPrimaryKey(param);
        return OK;
    }

    @Override
    public JsonResult info(Long id) {
        Menu menu = menuMapper.selectByPrimaryKey(id);
        return JsonResult.data(menu);
    }

}

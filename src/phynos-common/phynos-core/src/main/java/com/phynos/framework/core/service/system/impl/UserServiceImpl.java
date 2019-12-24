package com.phynos.framework.core.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.phynos.framework.core.json.JsonList;
import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.params.BaseParam;
import com.phynos.framework.core.service.BaseServiceImpl;
import com.phynos.framework.core.service.system.UserService;
import com.phynos.framework.dao.mapper.UserMapper;
import com.phynos.framework.dao.model.User;
import com.phynos.framework.dao.model.UserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author by Lupc
 * @date 2019/12/19.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    UserMapper userMapper;

    @Override
    public JsonResult list(BaseParam param) {
        Page page = PageHelper.startPage(param.getPageIndex(), param.getPageSize());
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        List<User> data = userMapper.selectByExample(userExample);
        long total = page.getTotal();
        PageHelper.clearPage();//一定要清理
        JsonList jl = JsonList.create(total, param.getPageIndex(), param.getPageSize(), data);
        return JsonResult.data(jl);
    }

    @Override
    public JsonResult add(User param) {
        userMapper.insertSelective(param);
        return OK;
    }

    @Override
    public JsonResult del(List<Long> ids) {
        ids.forEach(id -> userMapper.deleteByPrimaryKey(id));
        return OK;
    }

    @Override
    public JsonResult mod(User param) {
        userMapper.updateByPrimaryKey(param);
        return OK;
    }

    @Override
    public JsonResult info(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        return JsonResult.data(user);
    }

}

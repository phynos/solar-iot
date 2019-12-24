package com.phynos.framework.core.service.system;

import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.params.BaseParam;
import com.phynos.framework.dao.model.Role;

import java.util.List;

/**
* @Author: 1
* @Date: 2019-12-24 10:23:41
**/
public interface RoleService {

    JsonResult list(BaseParam param);

    JsonResult add(Role param);

    JsonResult del(List<Long> ids);

    JsonResult mod(Role param);

    JsonResult info(Long id);

}
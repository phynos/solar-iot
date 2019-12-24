package com.phynos.framework.core.service.system;

import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.params.BaseParam;
import com.phynos.framework.dao.model.Menu;

import java.util.List;

/**
* @Author: 1
* @Date: 2019-12-24 10:28:03
**/
public interface MenuService {

    JsonResult list(BaseParam param);

    JsonResult add(Menu param);

    JsonResult del(List<Long> ids);

    JsonResult mod(Menu param);

    JsonResult info(Long id);

}
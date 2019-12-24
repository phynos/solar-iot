package com.phynos.framework.core.service.system;

import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.params.BaseParam;
import com.phynos.framework.dao.model.User;

import java.util.List;

/**
 * @author by Lupc
 * @date 2019/12/19.
 */
public interface UserService {

    JsonResult list(BaseParam param);

    JsonResult add(User param);

    JsonResult del(List<Long> ids);

    JsonResult mod(User param);

    JsonResult info(Long id);

}

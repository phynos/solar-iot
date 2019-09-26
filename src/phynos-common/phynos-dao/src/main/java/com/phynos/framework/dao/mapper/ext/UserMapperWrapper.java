package com.phynos.framework.dao.mapper.ext;

import com.phynos.framework.dao.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author by Lupc
 * @date 2019/9/26.
 */
public interface UserMapperWrapper {

    User getByName(@Param("username") String username);

}

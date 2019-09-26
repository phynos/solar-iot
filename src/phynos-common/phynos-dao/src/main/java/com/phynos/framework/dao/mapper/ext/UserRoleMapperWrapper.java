package com.phynos.framework.dao.mapper.ext;

import com.phynos.framework.dao.model.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author by Lupc
 * @date 2019/9/26.
 */
public interface UserRoleMapperWrapper {

    List<UserRole> getRolesByUserId(@Param("userId") Long userId);

}

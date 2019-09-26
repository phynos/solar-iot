package com.phynos.framework.activiti.manager;

import com.phynos.framework.activiti.util.ActivitiUserUtils;
import com.phynos.framework.dao.mapper.RoleMapper;
import com.phynos.framework.dao.model.UserRole;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by Lupc
 * @date 2019/9/21.
 */
@Component
public class CustomUserEntityManager extends UserEntityManager {

    @Resource
    private UserMapperWrapper userMapperWrapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapperWrapper userRoleMapperWrapper;

    @Override
    public User findUserById(String userId) {
        com.tf.traffic.dao.model.User rocIdUser = userMapperWrapper.getByName(userId);
        //将自定义的user转化为activiti的类
        User userEntity = ActivitiUserUtils.toActivitiUser(rocIdUser);
        //返回activiti的实体类
        return userEntity;
    }

    @Override
    public List<Group> findGroupsByUser(final String userId) {
        if (userId == null) {
            return null;
        }
        List<Role> roleList = new ArrayList<>();
        com.tf.traffic.dao.model.User userAdmin = userMapperWrapper.getByName(userId);
        if (userAdmin == null) {
            return null;
        }
        List<UserRole> userRoleList = userRoleMapperWrapper.getRolesByUserId(userAdmin.getId());
        for (UserRole userrole : userRoleList
        ) {
            String roleId = userrole.getRoleId();
            Role role = roleMapper.selectByPrimaryKey(roleId);
            roleList.add(role);
        }
        List<Group> gs = ActivitiUserUtils.toActivitiGroups(roleList);
        return gs;
    }

}

package com.phynos.framework.activiti.manager;

import com.phynos.framework.dao.mapper.RoleMapper;
import com.tf.traffic.dao.mapper.RoleMapper;
import com.tf.traffic.dao.mapper.ext.UserMapperWrapper;
import com.tf.traffic.dao.mapper.ext.UserRoleMapperWrapper;
import com.tf.traffic.dao.model.Role;
import com.tf.traffic.dao.model.UserRole;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义角色管理
 *
 * @author by Lupc
 * @date 2019/9/21.
 */
@Component
public class CustomGroupEntityManager extends GroupEntityManager {

    @Resource
    private UserMapperWrapper userMapperWrapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapperWrapper userRoleMapperWrapper;

    @Override
    public Group createNewGroup(String groupId) {
        return super.createNewGroup(groupId);
    }

    /**
     * 查找角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Group> findGroupsByUser(final String userId) {
        if (userId == null) {
            return null;
        }
        //这里的userId 实际对应用户名
        com.tf.traffic.dao.model.User rocIdUser = userMapperWrapper.getByName(userId);
        List<UserRole> userRoleList = userRoleMapperWrapper.getRolesByUserId(rocIdUser.getId());
        List<Group> gs = new ArrayList<>();
        GroupEntity groupEntity;
        for (UserRole userRole : userRoleList
        ) {
            groupEntity = new GroupEntity();
            groupEntity.setRevision(1);
            groupEntity.setType("assignment");
            String roleId = userRole.getRoleId();
            Role role = roleMapper.selectByPrimaryKey(roleId);
            groupEntity.setId(role.getId() + "");
            groupEntity.setName(role.getRoleName());
            gs.add(groupEntity);
        }
        return gs;
    }

}

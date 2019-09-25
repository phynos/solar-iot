package com.phynos.framework.activiti.util;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 将业务中自己定义的用户、角色转化为activiti中使用的user、group
 *
 * @author by Lupc
 * @date 2019/9/21.
 */
public class ActivitiUserUtils {

    public static User toActivitiUser(com.tf.traffic.dao.model.User bUser) {
        User userEntity = new UserEntity();
        userEntity.setId(bUser.getUsername());
        userEntity.setFirstName(bUser.getNickname());
        userEntity.setLastName(bUser.getNickname());
        userEntity.setPassword(bUser.getPassword());
        return userEntity;
    }

    public static GroupEntity toActivitiGroup(Role sysRole) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setRevision(1);
        groupEntity.setType("assignment");
        groupEntity.setId(sysRole.getId() + "");
        groupEntity.setName(sysRole.getRoleName());
        return groupEntity;
    }

    public static List<Group> toActivitiGroups(List<Role> sysRoles) {
        List<Group> groups = new ArrayList<Group>();
        for (Role role : sysRoles
        ) {
            GroupEntity groupEntity = toActivitiGroup(role);
            groups.add(groupEntity);
        }
        return groups;
    }

}

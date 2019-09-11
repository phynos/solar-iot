package com.phynos.springboot.webapi.model.master;

public class Useradminrole {
    private Integer id;

    private Integer roleId;

    private Integer userAdminId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserAdminId() {
        return userAdminId;
    }

    public void setUserAdminId(Integer userAdminId) {
        this.userAdminId = userAdminId;
    }
}
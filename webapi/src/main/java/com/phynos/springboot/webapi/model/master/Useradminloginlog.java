package com.phynos.springboot.webapi.model.master;

import java.util.Date;

public class Useradminloginlog {
    private Integer id;

    private Integer userAdminId;

    private Date loginDatetime;

    private Integer platform;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserAdminId() {
        return userAdminId;
    }

    public void setUserAdminId(Integer userAdminId) {
        this.userAdminId = userAdminId;
    }

    public Date getLoginDatetime() {
        return loginDatetime;
    }

    public void setLoginDatetime(Date loginDatetime) {
        this.loginDatetime = loginDatetime;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
}
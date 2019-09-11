package com.phynos.springboot.webapi.model.master;

import java.util.Date;

public class Userclientloginlog {
    private Integer id;

    private Integer userClientId;

    private Date loginDatetime;

    private Integer platform;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserClientId() {
        return userClientId;
    }

    public void setUserClientId(Integer userClientId) {
        this.userClientId = userClientId;
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
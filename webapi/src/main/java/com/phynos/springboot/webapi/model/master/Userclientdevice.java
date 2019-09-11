package com.phynos.springboot.webapi.model.master;

import java.util.Date;

public class Userclientdevice {
    private Integer id;

    private Date createdDatetime;

    private Integer userClientId;

    private Integer deviceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Integer getUserClientId() {
        return userClientId;
    }

    public void setUserClientId(Integer userClientId) {
        this.userClientId = userClientId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}
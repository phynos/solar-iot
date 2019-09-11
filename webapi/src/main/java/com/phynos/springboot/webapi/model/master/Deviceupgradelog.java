package com.phynos.springboot.webapi.model.master;

import java.util.Date;

public class Deviceupgradelog {
    private Integer id;

    private Date createdDatetime;

    private Integer deviceId;

    private Integer stutus0;

    private Integer stutus1;

    private Integer stutus2;

    private Integer stutus3;

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

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getStutus0() {
        return stutus0;
    }

    public void setStutus0(Integer stutus0) {
        this.stutus0 = stutus0;
    }

    public Integer getStutus1() {
        return stutus1;
    }

    public void setStutus1(Integer stutus1) {
        this.stutus1 = stutus1;
    }

    public Integer getStutus2() {
        return stutus2;
    }

    public void setStutus2(Integer stutus2) {
        this.stutus2 = stutus2;
    }

    public Integer getStutus3() {
        return stutus3;
    }

    public void setStutus3(Integer stutus3) {
        this.stutus3 = stutus3;
    }
}
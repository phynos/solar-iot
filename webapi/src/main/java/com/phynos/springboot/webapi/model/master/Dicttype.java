package com.phynos.springboot.webapi.model.master;

import java.util.Date;

public class Dicttype {
    private Integer id;

    private String dictTypeName;

    private String dictTypeKey;

    private Boolean status;

    private Date createdDatetime;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictTypeName() {
        return dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName;
    }

    public String getDictTypeKey() {
        return dictTypeKey;
    }

    public void setDictTypeKey(String dictTypeKey) {
        this.dictTypeKey = dictTypeKey;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
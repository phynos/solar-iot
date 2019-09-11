package com.phynos.springboot.webapi.model.slave;

import java.util.Date;

public class Devicedata {
    private Integer id;

    private Date createdDatetime;

    private String username;

    private String email;

    private String mobilePhone;

    private String password;

    private Boolean emailValidated;

    private Boolean mobilePhoneValidated;

    private String nickname;

    private Boolean enabled;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEmailValidated() {
        return emailValidated;
    }

    public void setEmailValidated(Boolean emailValidated) {
        this.emailValidated = emailValidated;
    }

    public Boolean getMobilePhoneValidated() {
        return mobilePhoneValidated;
    }

    public void setMobilePhoneValidated(Boolean mobilePhoneValidated) {
        this.mobilePhoneValidated = mobilePhoneValidated;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
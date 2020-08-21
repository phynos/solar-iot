package com.phynos.framework.push.rabbitmq.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;

/**
 * 消息格式封装
 * @Author: Lupc
 * @Date: 2019/12/12 11:08
 **/
public class NoticeMessage {

    @JsonIgnore
    protected static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    static {
        //允许 json字符串有未知的属性
        JSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
    }

    private String id;
    private Date createTime;
    private String userId;
    private String noticeType;
    private String noticeTitle;
    private String noticeContent;
    private Integer status;
    private String bizType;
    private String bizId;
    private String bizTag;

    @Override
    public String toString() {
        try {
            return JSON_MAPPER.writeValueAsString(this);
        } catch (Exception e) {
            return "{}";
        }
    }

    @JsonIgnore
    public static NoticeMessage convert(String msg){
        try {
            NoticeMessage noticeMessage = JSON_MAPPER.readValue(msg,NoticeMessage.class);
            return noticeMessage;
        } catch (IOException e) {
            return null;
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getBizTag() {
        return bizTag;
    }

    public void setBizTag(String bizTag) {
        this.bizTag = bizTag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

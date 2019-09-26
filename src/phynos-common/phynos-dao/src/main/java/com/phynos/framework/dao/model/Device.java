package com.phynos.framework.dao.model;

import java.util.Date;

public class Device {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_device.id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_device.created_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Date createdDatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_device.sn
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private String sn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_device.remark
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_device.removed
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Boolean removed;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_device.removed_time
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Date removedTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_device.enabled
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Boolean enabled;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_device.id
     *
     * @return the value of sys_device.id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_device.id
     *
     * @param id the value for sys_device.id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_device.created_datetime
     *
     * @return the value of sys_device.created_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_device.created_datetime
     *
     * @param createdDatetime the value for sys_device.created_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_device.sn
     *
     * @return the value of sys_device.sn
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public String getSn() {
        return sn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_device.sn
     *
     * @param sn the value for sys_device.sn
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_device.remark
     *
     * @return the value of sys_device.remark
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_device.remark
     *
     * @param remark the value for sys_device.remark
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_device.removed
     *
     * @return the value of sys_device.removed
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Boolean getRemoved() {
        return removed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_device.removed
     *
     * @param removed the value for sys_device.removed
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_device.removed_time
     *
     * @return the value of sys_device.removed_time
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Date getRemovedTime() {
        return removedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_device.removed_time
     *
     * @param removedTime the value for sys_device.removed_time
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setRemovedTime(Date removedTime) {
        this.removedTime = removedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_device.enabled
     *
     * @return the value of sys_device.enabled
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_device.enabled
     *
     * @param enabled the value for sys_device.enabled
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
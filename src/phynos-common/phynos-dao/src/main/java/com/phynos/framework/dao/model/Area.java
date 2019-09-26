package com.phynos.framework.dao.model;

import java.util.Date;

public class Area {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_area.id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_area.area_name
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private String areaName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_area.parentId
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Long parentid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_area.sort
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Integer sort;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_area.area_code
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private String areaCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_area.created_user_id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Long createdUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_area.created_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Date createdDatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_area.update_user_id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Long updateUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_area.update_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Date updateDatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_area.remark
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_area.removed
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Boolean removed;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_area.id
     *
     * @return the value of sys_area.id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_area.id
     *
     * @param id the value for sys_area.id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_area.area_name
     *
     * @return the value of sys_area.area_name
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_area.area_name
     *
     * @param areaName the value for sys_area.area_name
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_area.parentId
     *
     * @return the value of sys_area.parentId
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_area.parentId
     *
     * @param parentid the value for sys_area.parentId
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_area.sort
     *
     * @return the value of sys_area.sort
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_area.sort
     *
     * @param sort the value for sys_area.sort
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_area.area_code
     *
     * @return the value of sys_area.area_code
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_area.area_code
     *
     * @param areaCode the value for sys_area.area_code
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_area.created_user_id
     *
     * @return the value of sys_area.created_user_id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Long getCreatedUserId() {
        return createdUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_area.created_user_id
     *
     * @param createdUserId the value for sys_area.created_user_id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setCreatedUserId(Long createdUserId) {
        this.createdUserId = createdUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_area.created_datetime
     *
     * @return the value of sys_area.created_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_area.created_datetime
     *
     * @param createdDatetime the value for sys_area.created_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_area.update_user_id
     *
     * @return the value of sys_area.update_user_id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_area.update_user_id
     *
     * @param updateUserId the value for sys_area.update_user_id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_area.update_datetime
     *
     * @return the value of sys_area.update_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_area.update_datetime
     *
     * @param updateDatetime the value for sys_area.update_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_area.remark
     *
     * @return the value of sys_area.remark
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_area.remark
     *
     * @param remark the value for sys_area.remark
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_area.removed
     *
     * @return the value of sys_area.removed
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Boolean getRemoved() {
        return removed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_area.removed
     *
     * @param removed the value for sys_area.removed
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }
}
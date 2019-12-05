package com.phynos.framework.dao.model;

import java.util.Date;

public class Dept {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dept.id
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dept.name
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dept.sort_number
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    private Integer sortNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dept.created_datetime
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    private Date createdDatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dept.update_datetime
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    private Date updateDatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dept.del_flag
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    private Boolean delFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dept.parent_id
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    private Long parentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dept.id
     *
     * @return the value of sys_dept.id
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dept.id
     *
     * @param id the value for sys_dept.id
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dept.name
     *
     * @return the value of sys_dept.name
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dept.name
     *
     * @param name the value for sys_dept.name
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dept.sort_number
     *
     * @return the value of sys_dept.sort_number
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    public Integer getSortNumber() {
        return sortNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dept.sort_number
     *
     * @param sortNumber the value for sys_dept.sort_number
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dept.created_datetime
     *
     * @return the value of sys_dept.created_datetime
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dept.created_datetime
     *
     * @param createdDatetime the value for sys_dept.created_datetime
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dept.update_datetime
     *
     * @return the value of sys_dept.update_datetime
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dept.update_datetime
     *
     * @param updateDatetime the value for sys_dept.update_datetime
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dept.del_flag
     *
     * @return the value of sys_dept.del_flag
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dept.del_flag
     *
     * @param delFlag the value for sys_dept.del_flag
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dept.parent_id
     *
     * @return the value of sys_dept.parent_id
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dept.parent_id
     *
     * @param parentId the value for sys_dept.parent_id
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
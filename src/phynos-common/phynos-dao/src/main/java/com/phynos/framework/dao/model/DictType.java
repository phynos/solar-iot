package com.phynos.framework.dao.model;

import java.util.Date;

public class DictType {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dict_type.id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dict_type.dict_type_name
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private String dictTypeName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dict_type.dict_type_key
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private String dictTypeKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dict_type.status
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Boolean status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dict_type.created_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Date createdDatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dict_type.remark
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dict_type.id
     *
     * @return the value of sys_dict_type.id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dict_type.id
     *
     * @param id the value for sys_dict_type.id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dict_type.dict_type_name
     *
     * @return the value of sys_dict_type.dict_type_name
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public String getDictTypeName() {
        return dictTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dict_type.dict_type_name
     *
     * @param dictTypeName the value for sys_dict_type.dict_type_name
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName == null ? null : dictTypeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dict_type.dict_type_key
     *
     * @return the value of sys_dict_type.dict_type_key
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public String getDictTypeKey() {
        return dictTypeKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dict_type.dict_type_key
     *
     * @param dictTypeKey the value for sys_dict_type.dict_type_key
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setDictTypeKey(String dictTypeKey) {
        this.dictTypeKey = dictTypeKey == null ? null : dictTypeKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dict_type.status
     *
     * @return the value of sys_dict_type.status
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dict_type.status
     *
     * @param status the value for sys_dict_type.status
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dict_type.created_datetime
     *
     * @return the value of sys_dict_type.created_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dict_type.created_datetime
     *
     * @param createdDatetime the value for sys_dict_type.created_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dict_type.remark
     *
     * @return the value of sys_dict_type.remark
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dict_type.remark
     *
     * @param remark the value for sys_dict_type.remark
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
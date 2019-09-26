package com.phynos.framework.dao.model;

import java.util.Date;

public class UserLoginLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_login_log.id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_login_log.user_id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_login_log.login_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Date loginDatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_login_log.platform
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    private Integer platform;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_login_log.id
     *
     * @return the value of sys_user_login_log.id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_login_log.id
     *
     * @param id the value for sys_user_login_log.id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_login_log.user_id
     *
     * @return the value of sys_user_login_log.user_id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_login_log.user_id
     *
     * @param userId the value for sys_user_login_log.user_id
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_login_log.login_datetime
     *
     * @return the value of sys_user_login_log.login_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Date getLoginDatetime() {
        return loginDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_login_log.login_datetime
     *
     * @param loginDatetime the value for sys_user_login_log.login_datetime
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setLoginDatetime(Date loginDatetime) {
        this.loginDatetime = loginDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_login_log.platform
     *
     * @return the value of sys_user_login_log.platform
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_login_log.platform
     *
     * @param platform the value for sys_user_login_log.platform
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
}
package com.phynos.framework.dao.model;

public class RoleAction {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_action.id
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_action.role_id
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    private Long roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_action.action_id
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    private Long actionId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_action.id
     *
     * @return the value of sys_role_action.id
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_action.id
     *
     * @param id the value for sys_role_action.id
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_action.role_id
     *
     * @return the value of sys_role_action.role_id
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_action.role_id
     *
     * @param roleId the value for sys_role_action.role_id
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_action.action_id
     *
     * @return the value of sys_role_action.action_id
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    public Long getActionId() {
        return actionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_action.action_id
     *
     * @param actionId the value for sys_role_action.action_id
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }
}
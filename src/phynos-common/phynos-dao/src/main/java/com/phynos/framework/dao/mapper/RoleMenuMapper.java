package com.phynos.framework.dao.mapper;

import com.phynos.framework.dao.model.RoleMenu;
import com.phynos.framework.dao.model.RoleMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    long countByExample(RoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int deleteByExample(RoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int insert(RoleMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int insertSelective(RoleMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    List<RoleMenu> selectByExample(RoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    RoleMenu selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int updateByExampleSelective(@Param("record") RoleMenu record, @Param("example") RoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int updateByExample(@Param("record") RoleMenu record, @Param("example") RoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int updateByPrimaryKeySelective(RoleMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int updateByPrimaryKey(RoleMenu record);
}
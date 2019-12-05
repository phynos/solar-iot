package com.phynos.framework.dao.mapper;

import com.phynos.framework.dao.model.Action;
import com.phynos.framework.dao.model.ActionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_action
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    long countByExample(ActionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_action
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int deleteByExample(ActionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_action
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_action
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int insert(Action record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_action
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int insertSelective(Action record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_action
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    List<Action> selectByExample(ActionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_action
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    Action selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_action
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int updateByExampleSelective(@Param("record") Action record, @Param("example") ActionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_action
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int updateByExample(@Param("record") Action record, @Param("example") ActionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_action
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int updateByPrimaryKeySelective(Action record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_action
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int updateByPrimaryKey(Action record);
}
package com.phynos.framework.dao.mapper;

import com.phynos.framework.dao.model.Parameter;
import com.phynos.framework.dao.model.ParameterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParameterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_parameter
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    long countByExample(ParameterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_parameter
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int deleteByExample(ParameterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_parameter
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_parameter
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int insert(Parameter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_parameter
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int insertSelective(Parameter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_parameter
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    List<Parameter> selectByExample(ParameterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_parameter
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    Parameter selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_parameter
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int updateByExampleSelective(@Param("record") Parameter record, @Param("example") ParameterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_parameter
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int updateByExample(@Param("record") Parameter record, @Param("example") ParameterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_parameter
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int updateByPrimaryKeySelective(Parameter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_parameter
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int updateByPrimaryKey(Parameter record);
}
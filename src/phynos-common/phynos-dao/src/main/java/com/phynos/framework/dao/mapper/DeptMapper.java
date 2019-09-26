package com.phynos.framework.dao.mapper;

import com.phynos.framework.dao.model.Dept;
import com.phynos.framework.dao.model.DeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    long countByExample(DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int deleteByExample(DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int insert(Dept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int insertSelective(Dept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    List<Dept> selectByExample(DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    Dept selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int updateByExampleSelective(@Param("record") Dept record, @Param("example") DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int updateByExample(@Param("record") Dept record, @Param("example") DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int updateByPrimaryKeySelective(Dept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dept
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int updateByPrimaryKey(Dept record);
}
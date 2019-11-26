package com.phynos.framework.dao.mapper;

import com.phynos.framework.dao.model.OperationLog;
import com.phynos.framework.dao.model.OperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperationLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    long countByExample(OperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    int deleteByExample(OperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    int insert(OperationLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    int insertSelective(OperationLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    List<OperationLog> selectByExample(OperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    OperationLog selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    int updateByExampleSelective(@Param("record") OperationLog record, @Param("example") OperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    int updateByExample(@Param("record") OperationLog record, @Param("example") OperationLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    int updateByPrimaryKeySelective(OperationLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operation_log
     *
     * @mbg.generated Tue Nov 26 17:49:29 CST 2019
     */
    int updateByPrimaryKey(OperationLog record);
}
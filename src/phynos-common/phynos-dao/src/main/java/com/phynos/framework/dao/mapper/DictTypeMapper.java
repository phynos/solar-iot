package com.phynos.framework.dao.mapper;

import com.phynos.framework.dao.model.DictType;
import com.phynos.framework.dao.model.DictTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DictTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    long countByExample(DictTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int deleteByExample(DictTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int insert(DictType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int insertSelective(DictType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    List<DictType> selectByExample(DictTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    DictType selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int updateByExampleSelective(@Param("record") DictType record, @Param("example") DictTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int updateByExample(@Param("record") DictType record, @Param("example") DictTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int updateByPrimaryKeySelective(DictType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Dec 05 14:03:34 CST 2019
     */
    int updateByPrimaryKey(DictType record);
}
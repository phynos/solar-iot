package com.phynos.framework.dao.mapper;

import com.phynos.framework.dao.model.Dict;
import com.phynos.framework.dao.model.DictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DictMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    long countByExample(DictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int deleteByExample(DictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int insert(Dict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int insertSelective(Dict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    List<Dict> selectByExample(DictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    Dict selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int updateByExampleSelective(@Param("record") Dict record, @Param("example") DictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int updateByExample(@Param("record") Dict record, @Param("example") DictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int updateByPrimaryKeySelective(Dict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    int updateByPrimaryKey(Dict record);
}
package com.phynos.springboot.webapi.dao.master;

import com.phynos.springboot.webapi.model.master.Parameter;
import com.phynos.springboot.webapi.model.master.ParameterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParameterMapper {
    long countByExample(ParameterExample example);

    int deleteByExample(ParameterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Parameter record);

    int insertSelective(Parameter record);

    List<Parameter> selectByExample(ParameterExample example);

    Parameter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Parameter record, @Param("example") ParameterExample example);

    int updateByExample(@Param("record") Parameter record, @Param("example") ParameterExample example);

    int updateByPrimaryKeySelective(Parameter record);

    int updateByPrimaryKey(Parameter record);
}
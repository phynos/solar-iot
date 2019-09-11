package com.phynos.springboot.webapi.dao.master;

import com.phynos.springboot.webapi.model.master.Operationlog;
import com.phynos.springboot.webapi.model.master.OperationlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperationlogMapper {
    long countByExample(OperationlogExample example);

    int deleteByExample(OperationlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Operationlog record);

    int insertSelective(Operationlog record);

    List<Operationlog> selectByExample(OperationlogExample example);

    Operationlog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Operationlog record, @Param("example") OperationlogExample example);

    int updateByExample(@Param("record") Operationlog record, @Param("example") OperationlogExample example);

    int updateByPrimaryKeySelective(Operationlog record);

    int updateByPrimaryKey(Operationlog record);
}
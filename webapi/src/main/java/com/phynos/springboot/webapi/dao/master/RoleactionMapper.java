package com.phynos.springboot.webapi.dao.master;

import com.phynos.springboot.webapi.model.master.Roleaction;
import com.phynos.springboot.webapi.model.master.RoleactionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleactionMapper {
    long countByExample(RoleactionExample example);

    int deleteByExample(RoleactionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Roleaction record);

    int insertSelective(Roleaction record);

    List<Roleaction> selectByExample(RoleactionExample example);

    Roleaction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Roleaction record, @Param("example") RoleactionExample example);

    int updateByExample(@Param("record") Roleaction record, @Param("example") RoleactionExample example);

    int updateByPrimaryKeySelective(Roleaction record);

    int updateByPrimaryKey(Roleaction record);
}
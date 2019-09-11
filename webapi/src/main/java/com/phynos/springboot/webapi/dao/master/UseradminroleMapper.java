package com.phynos.springboot.webapi.dao.master;

import com.phynos.springboot.webapi.model.master.Useradminrole;
import com.phynos.springboot.webapi.model.master.UseradminroleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UseradminroleMapper {
    long countByExample(UseradminroleExample example);

    int deleteByExample(UseradminroleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Useradminrole record);

    int insertSelective(Useradminrole record);

    List<Useradminrole> selectByExample(UseradminroleExample example);

    Useradminrole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Useradminrole record, @Param("example") UseradminroleExample example);

    int updateByExample(@Param("record") Useradminrole record, @Param("example") UseradminroleExample example);

    int updateByPrimaryKeySelective(Useradminrole record);

    int updateByPrimaryKey(Useradminrole record);
}
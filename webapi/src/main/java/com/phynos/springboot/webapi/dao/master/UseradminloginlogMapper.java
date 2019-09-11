package com.phynos.springboot.webapi.dao.master;

import com.phynos.springboot.webapi.model.master.Useradminloginlog;
import com.phynos.springboot.webapi.model.master.UseradminloginlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UseradminloginlogMapper {
    long countByExample(UseradminloginlogExample example);

    int deleteByExample(UseradminloginlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Useradminloginlog record);

    int insertSelective(Useradminloginlog record);

    List<Useradminloginlog> selectByExample(UseradminloginlogExample example);

    Useradminloginlog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Useradminloginlog record, @Param("example") UseradminloginlogExample example);

    int updateByExample(@Param("record") Useradminloginlog record, @Param("example") UseradminloginlogExample example);

    int updateByPrimaryKeySelective(Useradminloginlog record);

    int updateByPrimaryKey(Useradminloginlog record);
}
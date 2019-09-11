package com.phynos.springboot.webapi.dao.master;

import com.phynos.springboot.webapi.model.master.Rolemenu;
import com.phynos.springboot.webapi.model.master.RolemenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolemenuMapper {
    long countByExample(RolemenuExample example);

    int deleteByExample(RolemenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Rolemenu record);

    int insertSelective(Rolemenu record);

    List<Rolemenu> selectByExample(RolemenuExample example);

    Rolemenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Rolemenu record, @Param("example") RolemenuExample example);

    int updateByExample(@Param("record") Rolemenu record, @Param("example") RolemenuExample example);

    int updateByPrimaryKeySelective(Rolemenu record);

    int updateByPrimaryKey(Rolemenu record);
}
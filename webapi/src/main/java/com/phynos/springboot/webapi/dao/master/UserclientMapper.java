package com.phynos.springboot.webapi.dao.master;

import com.phynos.springboot.webapi.model.master.Userclient;
import com.phynos.springboot.webapi.model.master.UserclientExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserclientMapper {
    long countByExample(UserclientExample example);

    int deleteByExample(UserclientExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Userclient record);

    int insertSelective(Userclient record);

    List<Userclient> selectByExample(UserclientExample example);

    Userclient selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Userclient record, @Param("example") UserclientExample example);

    int updateByExample(@Param("record") Userclient record, @Param("example") UserclientExample example);

    int updateByPrimaryKeySelective(Userclient record);

    int updateByPrimaryKey(Userclient record);
}
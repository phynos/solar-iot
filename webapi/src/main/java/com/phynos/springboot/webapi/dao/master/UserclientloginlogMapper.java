package com.phynos.springboot.webapi.dao.master;

import com.phynos.springboot.webapi.model.master.Userclientloginlog;
import com.phynos.springboot.webapi.model.master.UserclientloginlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserclientloginlogMapper {
    long countByExample(UserclientloginlogExample example);

    int deleteByExample(UserclientloginlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Userclientloginlog record);

    int insertSelective(Userclientloginlog record);

    List<Userclientloginlog> selectByExample(UserclientloginlogExample example);

    Userclientloginlog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Userclientloginlog record, @Param("example") UserclientloginlogExample example);

    int updateByExample(@Param("record") Userclientloginlog record, @Param("example") UserclientloginlogExample example);

    int updateByPrimaryKeySelective(Userclientloginlog record);

    int updateByPrimaryKey(Userclientloginlog record);
}
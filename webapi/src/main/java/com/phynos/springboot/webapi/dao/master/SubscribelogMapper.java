package com.phynos.springboot.webapi.dao.master;

import com.phynos.springboot.webapi.model.master.Subscribelog;
import com.phynos.springboot.webapi.model.master.SubscribelogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubscribelogMapper {
    long countByExample(SubscribelogExample example);

    int deleteByExample(SubscribelogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Subscribelog record);

    int insertSelective(Subscribelog record);

    List<Subscribelog> selectByExample(SubscribelogExample example);

    Subscribelog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Subscribelog record, @Param("example") SubscribelogExample example);

    int updateByExample(@Param("record") Subscribelog record, @Param("example") SubscribelogExample example);

    int updateByPrimaryKeySelective(Subscribelog record);

    int updateByPrimaryKey(Subscribelog record);
}
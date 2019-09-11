package com.phynos.springboot.webapi.dao.master;

import com.phynos.springboot.webapi.model.master.Subscribe;
import com.phynos.springboot.webapi.model.master.SubscribeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubscribeMapper {
    long countByExample(SubscribeExample example);

    int deleteByExample(SubscribeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Subscribe record);

    int insertSelective(Subscribe record);

    List<Subscribe> selectByExample(SubscribeExample example);

    int updateByExampleSelective(@Param("record") Subscribe record, @Param("example") SubscribeExample example);

    int updateByExample(@Param("record") Subscribe record, @Param("example") SubscribeExample example);
}
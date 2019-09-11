package com.phynos.springboot.webapi.dao.slave;

import com.phynos.springboot.webapi.model.slave.Devicedata;
import com.phynos.springboot.webapi.model.slave.DevicedataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DevicedataMapper {
    long countByExample(DevicedataExample example);

    int deleteByExample(DevicedataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Devicedata record);

    int insertSelective(Devicedata record);

    List<Devicedata> selectByExample(DevicedataExample example);

    Devicedata selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Devicedata record, @Param("example") DevicedataExample example);

    int updateByExample(@Param("record") Devicedata record, @Param("example") DevicedataExample example);

    int updateByPrimaryKeySelective(Devicedata record);

    int updateByPrimaryKey(Devicedata record);
}
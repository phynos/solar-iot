package com.phynos.springboot.webapi.dao.master;

import com.phynos.springboot.webapi.model.master.Devicegeorecord;
import com.phynos.springboot.webapi.model.master.DevicegeorecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DevicegeorecordMapper {
    long countByExample(DevicegeorecordExample example);

    int deleteByExample(DevicegeorecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Devicegeorecord record);

    int insertSelective(Devicegeorecord record);

    List<Devicegeorecord> selectByExample(DevicegeorecordExample example);

    Devicegeorecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Devicegeorecord record, @Param("example") DevicegeorecordExample example);

    int updateByExample(@Param("record") Devicegeorecord record, @Param("example") DevicegeorecordExample example);

    int updateByPrimaryKeySelective(Devicegeorecord record);

    int updateByPrimaryKey(Devicegeorecord record);
}
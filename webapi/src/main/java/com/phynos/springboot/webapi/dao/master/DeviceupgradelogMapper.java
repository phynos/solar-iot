package com.phynos.springboot.webapi.dao.master;

import com.phynos.springboot.webapi.model.master.Deviceupgradelog;
import com.phynos.springboot.webapi.model.master.DeviceupgradelogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceupgradelogMapper {
    long countByExample(DeviceupgradelogExample example);

    int deleteByExample(DeviceupgradelogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Deviceupgradelog record);

    int insertSelective(Deviceupgradelog record);

    List<Deviceupgradelog> selectByExample(DeviceupgradelogExample example);

    Deviceupgradelog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Deviceupgradelog record, @Param("example") DeviceupgradelogExample example);

    int updateByExample(@Param("record") Deviceupgradelog record, @Param("example") DeviceupgradelogExample example);

    int updateByPrimaryKeySelective(Deviceupgradelog record);

    int updateByPrimaryKey(Deviceupgradelog record);
}
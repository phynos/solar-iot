package com.phynos.springboot.webapi.dao.master;

import com.phynos.springboot.webapi.model.master.Userclientdevice;
import com.phynos.springboot.webapi.model.master.UserclientdeviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserclientdeviceMapper {
    long countByExample(UserclientdeviceExample example);

    int deleteByExample(UserclientdeviceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Userclientdevice record);

    int insertSelective(Userclientdevice record);

    List<Userclientdevice> selectByExample(UserclientdeviceExample example);

    Userclientdevice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Userclientdevice record, @Param("example") UserclientdeviceExample example);

    int updateByExample(@Param("record") Userclientdevice record, @Param("example") UserclientdeviceExample example);

    int updateByPrimaryKeySelective(Userclientdevice record);

    int updateByPrimaryKey(Userclientdevice record);
}
package com.phynos.springboot.webapi.dao.master;

import com.phynos.springboot.webapi.model.master.Dicttype;
import com.phynos.springboot.webapi.model.master.DicttypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DicttypeMapper {
    long countByExample(DicttypeExample example);

    int deleteByExample(DicttypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Dicttype record);

    int insertSelective(Dicttype record);

    List<Dicttype> selectByExample(DicttypeExample example);

    Dicttype selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Dicttype record, @Param("example") DicttypeExample example);

    int updateByExample(@Param("record") Dicttype record, @Param("example") DicttypeExample example);

    int updateByPrimaryKeySelective(Dicttype record);

    int updateByPrimaryKey(Dicttype record);
}
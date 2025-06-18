package com.phynos.ds.mapper;

import com.phynos.ds.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2025/6/18 18:01
 */
public interface UserMapper {

    @Select("select * from user")
    List<User> listAll();

}

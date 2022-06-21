package com.phynos.solar.module.sys.mapper;

import com.phynos.solar.module.sys.entity.LogLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lupc
 * @since 2022-05-23
 */
public interface LogLoginMapper extends BaseMapper<LogLogin> {

    void batchInsert(@Param("logList") List<LogLogin> logList);

}

package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统参数表
 * </p>
 *
 * @author lupc
 * @since 2021-12-20
 */
@Getter
@Setter
@TableName("sys_parameter")
public class Parameter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 参数标签
     */
    private String paraLabel;

    /**
     * 参数键名
     */
    private String paraKey;

    /**
     * 参数键值
     */
    private String paraValue;

    /**
     * 参数类型：0=系统内置（不可删），1=用户新增
     */
    private Integer paraType;

    /**
     * 数据创建时间
     */
    private LocalDateTime createdDatetime;

    /**
     * 创建用户id
     */
    private Long createdUserId;

    /**
     * 更新时间
     */
    private LocalDateTime updateDatetime;

    /**
     * 更新用户id
     */
    private Long updateUserId;

    /**
     * 描述信息
     */
    private String remark;


}

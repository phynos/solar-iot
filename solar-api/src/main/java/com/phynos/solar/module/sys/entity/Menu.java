package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author lupc
 * @since 2021-12-13
 */
@Getter
@Setter
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 菜单名称
     */
    private String text;

    /**
     * 排序编号
     */
    private Integer sort;

    /**
     * 父菜单id
     */
    private Long pid;

    /**
     * 菜单类型：0=目录；1=菜单;2=按键
     */
    private Integer type;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 是否可见
     */
    private Boolean visible;

    /**
     * 权限字符
     */
    private String perms;

    /**
     * 描述信息
     */
    private String remark;


}

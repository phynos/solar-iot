package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author lupc
 * @since 2022-04-24
 */
@Getter
@Setter
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String pid;

    /**
     * 名称
     */
    private String menuName;

    private String menuPath;

    private String code;

    /**
     * 菜单层级
     */
    private Integer level;

    /**
     * 组件
     */
    private String component;


}

package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 字典项表
 * </p>
 *
 * @author lupc
 * @since 2022-04-24
 */
@Getter
@Setter
@TableName("sys_dict_item")
public class DictItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典类型
     */
    private String dictCode;

    /**
     * 编码
     */
    private String code;

    /**
     * 标签
     */
    private String label;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String note;

    /**
     * 父级ID
     */
    private String pid;


}

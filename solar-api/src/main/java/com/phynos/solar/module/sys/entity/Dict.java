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
 * 字典数据
 * </p>
 *
 * @author lupc
 * @since 2021-12-13
 */
@Getter
@Setter
@TableName("sys_dict")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 排序编号
     */
    private Integer sort;

    /**
     * 字典标签
     */
    private String dictLabel;

    /**
     * 字典值
     */
    private String dictValue;

    /**
     * 字典类型id
     */
    private Long dictTypeId;

    /**
     * 是否默认：0=否，=是
     */
    private Boolean isDefault;

    /**
     * 状态:0=禁用，1=启用
     */
    private Boolean status;

    /**
     * 数据创建时间
     */
    private Date createdDatetime;

    /**
     * 描述信息
     */
    private String remark;


}

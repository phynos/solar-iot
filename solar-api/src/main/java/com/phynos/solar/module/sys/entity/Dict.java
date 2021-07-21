package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 字典数据
 * </p>
 *
 * @author lupc
 * @since 2021-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 排序编号
     */
    private String sort;

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
    private String dictTypeId;

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

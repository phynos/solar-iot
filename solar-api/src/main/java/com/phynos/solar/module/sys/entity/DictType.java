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
 * 字典类型
 * </p>
 *
 * @author lupc
 * @since 2021-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict_type")
public class DictType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 字典类型名称
     */
    private String dictTypeName;

    /**
     * 字典类型唯一键
     */
    private String dictTypeKey;

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

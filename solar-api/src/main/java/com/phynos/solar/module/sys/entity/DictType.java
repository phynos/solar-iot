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
 * 字典类型
 * </p>
 *
 * @author lupc
 * @since 2021-12-13
 */
@Getter
@Setter
@TableName("sys_dict_type")
public class DictType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

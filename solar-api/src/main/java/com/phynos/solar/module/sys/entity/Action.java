package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author lupc
 * @since 2021-12-20
 */
@Getter
@Setter
@TableName("sys_action")
public class Action implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 描述信息
     */
    private String remark;


}

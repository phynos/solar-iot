package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lupc
 * @since 2022-04-24
 */
@Getter
@Setter
@TableName("sys_parameter")
public class Parameter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String paraLabel;

    private String paraKey;

    private String paraValue;

    private Integer paraType;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}

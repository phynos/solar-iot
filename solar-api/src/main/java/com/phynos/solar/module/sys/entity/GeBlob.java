package com.phynos.solar.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 通用二进制存储表
 * </p>
 *
 * @author lupc
 * @since 2021-12-20
 */
@Getter
@Setter
@TableName("sys_ge_blob")
public class GeBlob implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 大字节数据
     */
    private byte[] content;


}

package com.phynos.solar.oss;

import lombok.Builder;
import lombok.Data;

import java.io.ByteArrayInputStream;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/3/29 09:34
 */
@Builder
@Data
public class OssObject {

    private String parent;
    private String name;
    private int size;
    private String contentType;
    private ByteArrayInputStream bis;

}

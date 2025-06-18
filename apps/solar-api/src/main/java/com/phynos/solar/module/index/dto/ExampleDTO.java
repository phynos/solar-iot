package com.phynos.solar.module.index.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * @author lupc
 * @date 2021/3/26 14:05
 */
@Data
public class ExampleDTO {

    @NotNull(message = "a不能为空")
    private String a;

    @Max(value = 100, message = "最大值不能超过150")
    @Max(value = 0, message = "最小值不能小于0")
    private Integer b;

    @NotNull(message = "c不能为空")
    private Node c;

    @Data
    public static class Node {

        private String a;

        private String b;

    }

}

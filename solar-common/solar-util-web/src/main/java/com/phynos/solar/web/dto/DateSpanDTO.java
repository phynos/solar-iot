package com.phynos.solar.web.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2022/6/21 14:55
 */
@Data
public class DateSpanDTO {

    private LocalDate begin;

    private LocalDate end;

}

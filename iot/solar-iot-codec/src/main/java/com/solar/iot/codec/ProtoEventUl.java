package com.solar.iot.codec;

import lombok.Data;

/**
 * @author lupc
 * @date 2021/3/25 10:46
 */
@Data
public class ProtoEventUl {

    private Long time;

    /**
     * 交互id，由发起方维护，最小值=1
     */
    private Integer cid;

}

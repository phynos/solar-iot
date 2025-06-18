package com.solar.iot.codec;

import lombok.Data;

/**
 * @author lupc
 * @date 2021/3/25 11:13
 */
@Data
public class ProtoResp {

    /**
     * 交互id，由发起方维护，最小值=1
     */
    private Integer cid;

    private String code;

}

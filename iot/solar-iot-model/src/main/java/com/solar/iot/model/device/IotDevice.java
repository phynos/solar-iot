package com.solar.iot.model.device;

import com.solar.iot.model.product.IotProduct;

import java.time.LocalDateTime;

/**
 * IOT设备接口
 *
 * @author lupc
 */
public interface IotDevice extends IotProduct {

    String getSn();

    IotProduct getProduct();

    void refresh(String key, String value);

    /**
     * 完成设备相关初始化
     */
    void finishInit();

    /**
     * 是否在线
     * @return
     */
    boolean getOnline();

    /**
     * 最后一次数据更新时间
     * @return
     */
    LocalDateTime getLastDataTime();

}

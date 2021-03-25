package com.phynos.solar.codec.device;

import com.phynos.solar.codec.product.IotProduct;

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

}

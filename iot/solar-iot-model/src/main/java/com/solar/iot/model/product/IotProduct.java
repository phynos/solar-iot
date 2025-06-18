package com.solar.iot.model.product;

import com.solar.iot.model.device.IotAtrribute;
import com.solar.iot.model.device.IotEvent;
import com.solar.iot.model.device.IotService;

import java.util.Map;

/**
 * IOT产品模型
 *
 * @author lupc
 */
public interface IotProduct {

    /**
     * 产品编码，同一类产品相同
     *
     * @return
     */
    String getCode();

    /**
     * 产品供应商
     *
     * @return
     */
    String getVendor();

    /**
     * 产品版本
     *
     * @return
     */
    String getVersion();

    /**
     * 产品属性
     *
     * @return
     */
    Map<String, IotAtrribute> getAttrs();

    /**
     * 产品事件
     *
     * @return
     */
    Map<String, IotEvent> getEvents();

    /**
     * 产品服务
     *
     * @return
     */
    Map<String, IotService> getServices();

}

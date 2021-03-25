package com.phynos.solar.front.module.device;

import org.springframework.messaging.Message;

public interface DeviceService {

    void data(Message<?> message);

    void data(String topic, String payload, Integer qos);

    void handleData(String sn, String json);

    /**
     * 保存数据到数据库
     */
    void saveData2Db();

}

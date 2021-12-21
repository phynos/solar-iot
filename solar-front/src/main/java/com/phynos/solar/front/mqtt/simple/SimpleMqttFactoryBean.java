package com.phynos.solar.front.mqtt.simple;

import com.phynos.solar.front.mqtt.MqttProperties;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 负责创建mqttclient
 *
 * @author lupc
 * @date 2021/12/17 15:10
 */
@Slf4j
public class SimpleMqttFactoryBean implements MqttCallback, FactoryBean<MqttClient>, InitializingBean, DisposableBean {

    MqttClient myClient;
    MqttConnectOptions connOpt;

    static final String CLIENT_ID = "server_001";

    static final String M2MIO_DOMAIN = "m2m.phynos.com";
    static final String M2MIO_STUFF = "things";
    static final String M2MIO_THING = "007";

    @Setter
    MqttProperties mqttProperties;

    @Override
    public MqttClient getObject() throws Exception {
        return this.myClient;
    }

    @Override
    public Class<?> getObjectType() {
        return this.myClient != null ? myClient.getClass() : MqttClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void destroy() throws Exception {
        myClient.disconnect();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        prepareClient();
    }

    @Override
    public void connectionLost(Throwable cause) {
        log.debug("Connection lost!");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.debug("-------------------------------------------------");
        log.debug("| Topic:" + topic);
        log.debug("| Message: " + new String(message.getPayload()));
        log.debug("-------------------------------------------------");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    private void prepareClient() {
        connOpt = new MqttConnectOptions();
        connOpt.setCleanSession(true);
        connOpt.setKeepAliveInterval(30);
        connOpt.setUserName(mqttProperties.getUsername());
        connOpt.setPassword(mqttProperties.getPassword().toCharArray());

        connect();
    }

    public void connect() {
        String clientID = CLIENT_ID;
        try {
            myClient = new MqttClient(mqttProperties.getUrl(), clientID);
            myClient.setCallback(this);
            myClient.connect(connOpt);
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        log.debug("Connected to {}", mqttProperties.getUrl());
    }

    public void disconnect() {
        try {
            myClient.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

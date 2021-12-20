package com.phynos.solar.front.mqtt.simple;

import com.phynos.solar.front.mqtt.MqttProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;

@Slf4j
public class SimpleMqttClient implements MqttCallback {

    private MqttProperties mqttProperties;

    MqttClient myClient;
    MqttConnectOptions connOpt;

    static final String CLIENT_ID = "server_001";

    static final String M2MIO_DOMAIN = "m2m.phynos.com";
    static final String M2MIO_STUFF = "things";
    static final String M2MIO_THING = "007";

    public SimpleMqttClient(MqttProperties mqttProperties) {
        this.mqttProperties = mqttProperties;
    }

    public void connect() {
        // setup MQTT Client
        String clientID = CLIENT_ID;
        connOpt = new MqttConnectOptions();

        connOpt.setCleanSession(true);
        connOpt.setKeepAliveInterval(30);
        connOpt.setUserName(mqttProperties.getUsername());
        connOpt.setPassword(mqttProperties.getPassword().toCharArray());

        // Connect to Broker
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
        // disconnect
        try {
            myClient.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * connectionLost
     * This callback is invoked upon losing the MQTT connection.
     */
    @Override
    public void connectionLost(Throwable t) {
        log.debug("Connection lost!");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // TODO Auto-generated method stub

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.debug("-------------------------------------------------");
        log.debug("| Topic:" + topic);
        log.debug("| Message: " + new String(message.getPayload()));
        log.debug("-------------------------------------------------");
    }

    public void subscriber() {
        // setup topic
        // topics on m2m.io are in the form <domain>/<stuff>/<thing>
        String myTopic = M2MIO_DOMAIN + "/" + M2MIO_STUFF + "/" + M2MIO_THING;
        MqttTopic topic = myClient.getTopic(myTopic);

        // subscribe to topic if subscriber
        try {
            int subQoS = 0;
            myClient.subscribe(myTopic, subQoS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publisher() {
        String myTopic = M2MIO_DOMAIN + "/" + M2MIO_STUFF + "/" + M2MIO_THING;
        MqttTopic topic = myClient.getTopic(myTopic);
        for (int i = 1; i <= 10; i++) {
            String pubMsg = "{\"pubmsg\":" + i + "}";
            int pubQoS = 0;
            MqttMessage message = new MqttMessage(pubMsg.getBytes());
            message.setQos(pubQoS);
            message.setRetained(false);

            // Publish the message
            log.debug("Publishing to topic \"" + topic + "\" qos " + pubQoS);
            MqttDeliveryToken token = null;
            try {
                // publish message to broker
                token = topic.publish(message);
                // Wait until the message has been delivered to the broker
                token.waitForCompletion();
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

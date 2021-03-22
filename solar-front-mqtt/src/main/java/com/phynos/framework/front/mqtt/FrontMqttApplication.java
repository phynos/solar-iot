package com.phynos.framework.front.mqtt;

import com.phynos.framework.front.mqtt.autoconfig.MqttV3Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author by lupc
 * @date 2020-09-29 15:12
 */
@SpringBootApplication
public class FrontMqttApplication implements CommandLineRunner {


    @Autowired
    MqttV3Template mqttV3Template;

    public static void main(String[] args) {
        SpringApplication.run(FrontMqttApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mqttV3Template.sendToMqtt("/test", "server 222");
        System.in.read();
    }

}

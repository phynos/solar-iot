package com.phynos.solar.data;


import com.phynos.solar.data.autoconfig.MqttV3Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author by lupc
 * @date 2020-09-29 15:12
 */
@EnableScheduling
@SpringBootApplication
public class SolarDataApplication implements CommandLineRunner {

    @Autowired
    MqttV3Template mqttV3Template;

    public static void main(String[] args) {
        SpringApplication.run(SolarDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mqttV3Template.sendToMqtt("/test", "server 222");
        System.in.read();
    }

}

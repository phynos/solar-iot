package com.phynos.framework.front.mqtt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author by lupc
 * @date 2020-09-29 15:12
 */
@SpringBootApplication
public class FrontMqttApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FrontMqttApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SimpleMqttClient smc = new SimpleMqttClient();
        smc.runClient();
    }

}

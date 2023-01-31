package com.eny.sensordataconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableScheduling //TODO: bunu kaldır producerı silerken
public class SensorDataConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorDataConsumerApplication.class, args);
	}

}

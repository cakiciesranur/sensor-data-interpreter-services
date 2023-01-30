package com.eny.sensordataconsumer.client;

import com.eny.sensordataconsumer.client.payload.OptimalDeviceTemperature;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "device-service", url = "${feign.client.device-service.url}")
public interface DeviceServiceClient {

    @GetMapping("/optimalTemperature/{deviceType}")
    OptimalDeviceTemperature getOptimalTempByDeviceType(@PathVariable(value = "deviceType") String deviceType);
}
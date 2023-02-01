package com.eny.sensordatainterpreter.client;

import com.eny.sensordatainterpreter.client.payload.OptimalDeviceTemperature;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "device-service", url = "${feign.client.device-service.url}")

public interface DeviceServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/optimalTemperature/{deviceType}", consumes = "application/json")
    OptimalDeviceTemperature getOptimalTempByDeviceType(@RequestParam(value = "deviceType") String deviceType);
}

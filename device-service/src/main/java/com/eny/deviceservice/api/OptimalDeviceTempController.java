package com.eny.deviceservice.api;

import com.eny.deviceservice.datamodel.OptimalDeviceTemperature;
import com.eny.deviceservice.service.OptimalDeviceTempService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
@RequiredArgsConstructor
public class OptimalDeviceTempController {

    private final OptimalDeviceTempService service;

    @GetMapping("/")
    public List<OptimalDeviceTemperature> getAllOptimumTempData() {
        return service.getAllOptimumTempData();
    }

    @GetMapping({"/optimalTemperature/{deviceType}"})
    @ResponseBody
    public String getOptimalTempByDeviceType(@PathVariable String deviceType) {
        return "ID: " + deviceType;
    }
}

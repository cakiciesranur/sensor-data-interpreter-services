package com.eny.deviceservice.api;

import com.eny.deviceservice.datamodel.OptimalDeviceTemperature;
import com.eny.deviceservice.service.OptimalDeviceTempService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/optimalTemperature")
@RequiredArgsConstructor
public class OptimalDeviceTempController {

    private final OptimalDeviceTempService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<OptimalDeviceTemperature>> getAllOptimumTempData() {
        List<OptimalDeviceTemperature> allOptTempData = service.getAllOptimumTempData();
        return new ResponseEntity<>(allOptTempData, HttpStatus.OK);
    }

    @GetMapping(value = {"/{deviceType}"}, produces = "application/json")
    @ResponseBody
    public ResponseEntity<OptimalDeviceTemperature> getOptimalTempByDeviceType(@PathVariable String deviceType) {
        Optional<OptimalDeviceTemperature> optTemperatureData = service.getOptimalTemperatureByDeviceType(deviceType);

        return optTemperatureData
                .map(optimalDeviceTemperature -> new ResponseEntity<>(optimalDeviceTemperature, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

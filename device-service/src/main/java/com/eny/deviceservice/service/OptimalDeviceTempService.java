package com.eny.deviceservice.service;

import com.eny.deviceservice.datamodel.OptimalDeviceTemperature;
import com.eny.deviceservice.repository.OptimalDeviceTemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OptimalDeviceTempService {
    private final OptimalDeviceTemperatureRepository repository;

    public List<OptimalDeviceTemperature> getAllOptimumTempData() {
        return repository.findAll();
    }

    public Optional<OptimalDeviceTemperature> getOptimalTemperatureById(Long id) {
        return repository.findById(id);
    }

    public Optional<OptimalDeviceTemperature> getOptimalTemperatureByDeviceType(String deviceType) {
        return repository.findByDeviceType(deviceType);
    }

}

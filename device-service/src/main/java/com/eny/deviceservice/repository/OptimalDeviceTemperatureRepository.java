package com.eny.deviceservice.repository;

import com.eny.deviceservice.datamodel.OptimalDeviceTemperature;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OptimalDeviceTemperatureRepository extends JpaRepository<OptimalDeviceTemperature, Long> {
    Optional<OptimalDeviceTemperature> findByDeviceType(String deviceType);
}
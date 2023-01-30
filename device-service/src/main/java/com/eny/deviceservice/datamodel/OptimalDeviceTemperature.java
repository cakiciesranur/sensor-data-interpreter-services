package com.eny.deviceservice.datamodel;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "optimal_temperature")
public class OptimalDeviceTemperature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "device_type", unique = true)
    @NotNull
    private String deviceType;

    @NotNull
    @Column(name = "temperature")
    private double optimalTemp;

}


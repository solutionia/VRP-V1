/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.entities;

/**
 *
 * @author Kelv
 */
public class VehicleType {
    private String  vehicleTypeId;
    private Double capacity;
    private Double costPerDistanceKm;

    public VehicleType() {
    }

    public VehicleType(String vehicleTypeId, Double capacity, Double costPerDistanceKm) {
        this.vehicleTypeId = vehicleTypeId;
        this.capacity = capacity;
        this.costPerDistanceKm = costPerDistanceKm;
    }

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }


    public Double getCostPerDistanceKm() {
        return costPerDistanceKm;
    }

    public void setCostPerDistanceKm(Double costPerDistanceKm) {
        this.costPerDistanceKm = costPerDistanceKm;
    }
    
    
}

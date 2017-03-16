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
public class Location {
    private String locationId;
    private Double latitude;
    private Double longitude;
    private String address;
    private String codeUbigeo;

    public Location() {
    }

    public Location(String locationId, Double latitude, Double longitude, String address, String codigoUbigeo) {
        this.locationId = locationId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.codeUbigeo = codigoUbigeo;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCodigoUbigeo() {
        return codeUbigeo;
    }

    public void setCodigoUbigeo(String codigoUbigeo) {
        this.codeUbigeo = codigoUbigeo;
    }
    
    
        
    
}

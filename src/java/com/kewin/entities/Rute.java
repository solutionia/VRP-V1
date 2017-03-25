/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.entities;

import java.util.List;

/**
 *
 * @author Kelv
 */
public class Rute {
    
 
    private double costPerDistanceKm;
    private Vehicle vehicle;
    private List<Node_Client> clients;
    
    public Rute() {
    }
    

    public Rute( double cost, Vehicle vehicle, List<Node_Client> clients) {
     
        this.costPerDistanceKm = cost;
        this.vehicle = vehicle;
        this.clients = clients;
    }  

    public double getCost() {
        return costPerDistanceKm;
    }

    public void setCost(double cost) {
        this.costPerDistanceKm = cost;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Node_Client> getClients() {
        return clients;
    }

    public void setClients(List<Node_Client> clients) {
        this.clients = clients;
    }
    
}

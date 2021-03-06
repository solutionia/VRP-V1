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
public class Node_Depot {
    
    private String nodeDepotId;
    private String name;
    private double capacity; 
    private Location location;

    public Node_Depot() {
    }

    public Node_Depot(String nodeDepotId, String name, double capacity, Location location) {
        this.nodeDepotId = nodeDepotId;
        this.name = name;
        this.capacity = capacity;
        this.location = location;
    }

    public String getNodeDepotId() {
        return nodeDepotId;
    }

    public void setNodeDepotId(String nodeDepotId) {
        this.nodeDepotId = nodeDepotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location locationStart) {
        this.location = locationStart;
    }

}

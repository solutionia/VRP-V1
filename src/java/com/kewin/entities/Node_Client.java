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
public class Node_Client {

    //Formato String 
    private String nodeClientId;
    //Formato String 
    private String description;
    //Formado int 
    private int quantityOrder;
    //Formato especificado por el objeto
    private Location location;
    //Formato int
    private int sequence;
    //Formato especificado por el objeto
    private TimeWindow timeWindow;
    //Verifica si es un deposito o no lo es.
    private boolean isDepot;

    public Node_Client() {
    }

    public Node_Client(String nodeClientId, String description, int quantityOrder, Location location, int sequence, TimeWindow timeWindow) {
        this.nodeClientId = nodeClientId;
        this.description = description;
        this.quantityOrder = quantityOrder;
        this.location = location;
        this.sequence = sequence;
        this.timeWindow = timeWindow;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNodeClientId() {
        return nodeClientId;
    }

    public void setNodeClientId(String nodeClientId) {
        this.nodeClientId = nodeClientId;
    }

    public int getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(int quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public TimeWindow getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(TimeWindow timeWindow) {
        this.timeWindow = timeWindow;
    }

    public boolean isIsDepot() {
        return isDepot;
    }

    public void setIsDepot(boolean isDepot) {
        this.isDepot = isDepot;
    }
    
    

}

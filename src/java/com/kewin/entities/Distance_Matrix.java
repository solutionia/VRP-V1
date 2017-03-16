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
public class Distance_Matrix {

    private String status;
    private String[] destination_addresses;
    private String[] origin_addresses;
    private Item[] rows;
    
    public Distance_Matrix(){}

    public Distance_Matrix(String status, String[] destination_addresses, String[] origin_addresses, Item[] rows) {
        this.status = status;
        this.destination_addresses = destination_addresses;
        this.origin_addresses = origin_addresses;
        this.rows = rows;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getDestination_addresses() {
        return destination_addresses;
    }

    public void setDestination_addresses(String[] destination_addresses) {
        this.destination_addresses = destination_addresses;
    }

    public String[] getOrigin_addresses() {
        return origin_addresses;
    }

    public void setOrigin_addresses(String[] origin_addresses) {
        this.origin_addresses = origin_addresses;
    }

    public Item[] getRows() {
        return rows;
    }

    public void setRows(Item[] rows) {
        this.rows = rows;
    }   

}

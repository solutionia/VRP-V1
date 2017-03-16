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
public class ProblemJson {
    private String id;
    private List<Node_Depot> depots;  
    private List<Node_Client> clients;
    private String apiKey;

    public ProblemJson() {
    }

    public ProblemJson(String id, List<Node_Depot> depots, List<Node_Client> clients, String apiKey) {
        this.id = id;
        this.depots = depots;
        this.clients = clients;
        this.apiKey = apiKey;
    }

  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Node_Depot> getDepots() {
        return depots;
    }

    public void setDepots(List<Node_Depot> depots) {
        this.depots = depots;
    }

    public List<Node_Client> getClients() {
        return clients;
    }

    public void setClients(List<Node_Client> clients) {
        this.clients = clients;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.entities;

import com.kewin.utils.Constans;
import java.util.List;
import javax.ws.rs.Produces;

/**
 *
 * @author Kelv
 * Entidad de entrada para la planificacion de rutas. 
 * Informacion (Problema) que ingresa para el desarrollo del algoritmo. 
 */
@Produces(Constans.MEDYA_TYPE_APPLICATION_JSON)
public class Problem {
    //Formato String
    private String ProblemId;
    //Formaro dd/MM/yyyy HH:mm:ss
    private String dateCurrent;
    //Formato String
    private String description;
    //Formato especificado como un objeto
    private Node_Depot node_Depot;
    //Formato especificado como un objeto
    private List<Node_Client> clients ;
    //Formaro especificado por el objeto
    private List<Vehicle>  vehicles;
    //Formaro especificado 
    private String apiKeyGoogle;
    private boolean finite;

    public Problem() {
    }

    public Problem(String ProblemId, String dateCurrent, String description, Node_Depot node_Depot, List<Node_Client> clients, List<Vehicle> vehicles, String apiKeyGoogle, boolean isFinite) {
        this.ProblemId = ProblemId;
        this.dateCurrent = dateCurrent;
        this.description = description;
        this.node_Depot = node_Depot;
        this.clients = clients;
        this.vehicles = vehicles;
        this.apiKeyGoogle = apiKeyGoogle;
        this.finite = isFinite;
    }

    public boolean isIsFinite() {
        return finite;
    }

    public void setIsFinite(boolean isFinite) {
        this.finite = isFinite;
    }

   

    public String getProblemId() {
        return ProblemId;
    }

    public void setProblemId(String ProblemId) {
        this.ProblemId = ProblemId;
    }

    public String getDateCurrent() {
        return dateCurrent;
    }

    public void setDateCurrent(String dateCurrent) {
        this.dateCurrent = dateCurrent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Node_Depot getNode_Depot() {
        return node_Depot;
    }

    public void setNode_Depot(Node_Depot node_Depot) {
        this.node_Depot = node_Depot;
    }

    public List<Node_Client> getClients() {
        return clients;
    }

    public void setClients(List<Node_Client> clients) {
        this.clients = clients;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public String getApiKeyGoogle() {
        return apiKeyGoogle;
    }

    public void setApiKeyGoogle(String apiKeyGoogle) {
        this.apiKeyGoogle = apiKeyGoogle;
    }    
}

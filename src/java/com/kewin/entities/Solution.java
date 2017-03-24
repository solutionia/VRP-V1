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
public class Solution {

    private String solutionId;
    private String date;
    private Double costPerFlexDistanceKm;
    private Node_Depot depots;
    private List<Rute> rutes;
    //Soluciones extra
    /*
    Si no se abastece con la flota , sugerir otro vehiculo o retornar un vehiculo que ta ha atendido.
     */
    //Si es falso, rutaesEctra !=  null 
    private boolean isRutesCompleted;
    private String messageAdicional;
    //Solo si la configuracion es con flota exacta
    private List<Node_Client> unassignedClients;
    
    
    //Campos para el manejo de errores
    private boolean isSuccess;
    private String messageError;
    
    private Problem problem;

    public Solution() {
    }

    public Solution(String solutionId, String date, Double costPerFlexDistanceKm, Node_Depot depots, List<Rute> rutes, boolean isRutesCompleted, String messageAdicional, List<Node_Client> unassignedClients, boolean isSuccess, String messageError, Problem problem) {
        this.solutionId = solutionId;
        this.date = date;
        this.costPerFlexDistanceKm = costPerFlexDistanceKm;
        this.depots = depots;
        this.rutes = rutes;
        this.isRutesCompleted = isRutesCompleted;
        this.messageAdicional = messageAdicional;
        this.unassignedClients = unassignedClients;
        this.isSuccess = isSuccess;
        this.messageError = messageError;
        this.problem = problem;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

 

    public List<Node_Client> getUnassignedClients() {
        return unassignedClients;
    }

    public void setUnassignedClients(List<Node_Client> unassignedClients) {
        this.unassignedClients = unassignedClients;
    }
    
    


    public boolean isIsRutesCompleted() {
        return isRutesCompleted;
    }

    public void setIsRutesCompleted(boolean isRutesCompleted) {
        this.isRutesCompleted = isRutesCompleted;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
    
    


    public String getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getCostPerFlexDistanceKm() {
        return costPerFlexDistanceKm;
    }

    public void setCostPerFlexDistanceKm(Double costPerFlexDistanceKm) {
        this.costPerFlexDistanceKm = costPerFlexDistanceKm;
    }

    public Node_Depot getDepots() {
        return depots;
    }

    public void setDepots(Node_Depot depots) {
        this.depots = depots;
    }

    public List<Rute> getRutes() {
        return rutes;
    }

    public void setRutes(List<Rute> rutes) {
        this.rutes = rutes;
    }

   

    public String getMessageAdicional() {
        return messageAdicional;
    }

    public void setMessageAdicional(String messageAdicional) {
        this.messageAdicional = messageAdicional;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.runTest;

import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.kewin.controller.InterSolutionController;
import com.kewin.entities.Location;
import com.kewin.entities.Node_Client;
import com.kewin.entities.Node_Depot;
import com.kewin.entities.Problem;
import com.kewin.entities.TimeWindow;
import com.kewin.entities.Vehicle;
import com.kewin.entities.VehicleType;
import com.kewin.utils.Constans;
import com.kewin.utils.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Kelv
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
            Problem problem;
        List<Node_Client> clients = new ArrayList<>();
        clients.add(new Node_Client(
                "CLI0001",
                "SERVICIOS SALES Y DERIVADOS",
                2000,
                new Location(
                        "LOC0003",
                        -11.9772282,
                        -76.7716575,
                        "Nogales 752, Chaclacayo",
                        "150103"),
                0,
                new TimeWindow(
                        "TIM0001",
                        "2:00:00",
                        "3:00:00")));

        clients.add(new Node_Client(
                "CLI0002",
                "SERVICIOS EL BRUJO - VIRU",
                2000,
                new Location(
                        "LOC0004",
                        -12.0378718,
                        -76.9678016,
                        "Av Imperial 411, Distrito de Lima 15009",
                        "150103"),
                0,
                new TimeWindow(
                        "TIM00002",
                        "6:00:00",
                        "7:00:00")));

        clients.add(new Node_Client(
                "CLI0003",
                "ENVASES VENTANILLA S.A",
                4000,
                new Location(
                        "LOC0005",
                        -11.987782,
                        -76.9379192,
                        "5 de Agosto, Distrito de Lima",
                        "150103"),
                0,
                new TimeWindow(
                        "TIM00003",
                        "13:00:00",
                        "15:00:00")));
        List<Vehicle> vehicles = new ArrayList<>();
        VehicleType vehicleType = new VehicleType (
                "VEHTYP0001",
                4000.00,
                11.00);
        VehicleType vehicleType2 = new VehicleType (
                "VEHTYP0002",
                2000.00,
                100.00);
        
         VehicleType vehicleType3 = new VehicleType (
                "VEHTYP0003",
                2000.00,
                123.0);


        vehicles.add(new Vehicle(
                "VEH0001",
                "VEHICULO 1",
                "QWE123",
                vehicleType));
        vehicles.add(new Vehicle(
                "VEH0002",
                "VEHICULO 2",
                "ASD456",
                vehicleType2));
       /* vehicles.add(new Vehicle(
                "VEH0003",
                "VEHICULO 3",
                "ZXC789",
                vehicleType3));*/

        problem = new Problem(
                "PRO0001",
                "29/01/2017",
                
                "Generar solucion para la empresa Energigas SAC",
                new Node_Depot(
                        "DEP0001",
                        "Deposito Central",
                        6000,
                        new Location(
                                "LOC0001",
                                -12.0178427,
                                -76.900919,
                                "Av. Nicolás de Ayllón 7548, Lima",
                                "150103"),
                        //No Utilizar XD
                        new Location(
                                "LOC0002",
                                -12.0178427,
                                -76.900919,
                                "Av. Nicolás de Ayllón 7548, Lima",
                                "150103")),
                clients,
                vehicles,
                Constans.API_KEY,false);
        
         InterSolutionController controller = new InterSolutionController(problem);
        controller.execute();
       
      //  VehicleRoutingProblemSolution solu = controller.getSolution();
        String jsonInString;
   
       
        

    }

}

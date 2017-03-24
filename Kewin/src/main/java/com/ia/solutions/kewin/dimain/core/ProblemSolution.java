/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.solutions.kewin.dimain.core;

import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.cost.VehicleRoutingTransportCosts;
import com.graphhopper.jsprit.core.problem.job.Job;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.solution.route.VehicleRoute;
import com.graphhopper.jsprit.core.problem.solution.route.activity.TimeWindow;
import com.graphhopper.jsprit.core.problem.solution.route.activity.TourActivity;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.reporting.SolutionPrinter;
import com.graphhopper.jsprit.core.util.Solutions;
import com.graphhopper.jsprit.core.util.VehicleRoutingTransportCostsMatrix;
import com.ia.solutions.kewin.dimain.httpRequest.HttpClient;
import com.ia.solutions.kewin.dimain.httpRequest.HttpClientListener;
import com.ia.solutions.kewin.domain.Constans;
import com.ia.solutions.kewin.domain.Utils;
import com.ia.solutions.kewin.entities.Distance_Matrix;
import com.ia.solutions.kewin.entities.Element;
import com.ia.solutions.kewin.entities.Node_Client;
import com.ia.solutions.kewin.entities.Problem;
import com.ia.solutions.kewin.entities.Rute;
import com.ia.solutions.kewin.entities.Solution;
import com.ia.solutions.kewin.entities.Vehicle;
import com.ia.solutions.kewin.rest.solution.SolutionNotification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Vaio
 */
public class ProblemSolution implements HttpClientListener {

    private static final String TAG = "ProblemSolution";
    private Problem problem;
    private VehicleRoutingTransportCostsMatrix.Builder costMatrixBuilder;
    private VehicleRoutingTransportCosts costMatrix;
    private VehicleRoutingProblem vrp;
    private Distance_Matrix distance_Matrix;
    private HashMap<Integer, Node_Client> hashMaNodes;
    private Solution solution;
    private SolutionNotification notification;
    private String error = "No instanciado";

    public ProblemSolution(SolutionNotification notification) {
        this.hashMaNodes = new HashMap<Integer, Node_Client>();
        this.costMatrixBuilder = VehicleRoutingTransportCostsMatrix.Builder.newInstance(Constans.ISSYMETRYC_FALSE);
        this.notification = notification;

    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public void runSolution() {
        execute();
    }

    private String generateTransportDistanceTimeGoTo() {
        String latLongs = "";
        latLongs = latLongs + "|" + problem.getNode_Depot().getLocation().getLatitude() + "," + problem.getNode_Depot().getLocation().getLongitude();
        Node_Client clientDepot = new Node_Client();
        clientDepot.setNodeClientId(problem.getNode_Depot().getNodeDepotId());
        clientDepot.setLocation(problem.getNode_Depot().getLocation());
        clientDepot.setIsDepot(true);
        int o = 0;
        for (int i = 0; i < problem.getClients().size(); i++) {

            Node_Client client = problem.getClients().get(i);
            hashMaNodes.put(i, client);

            latLongs = latLongs + "|" + client.getLocation().getLatitude() + "," + client.getLocation().getLongitude();
            o = i;
        }
        hashMaNodes.put(o + 1, clientDepot);
        return latLongs;
    }

    private void execute() {
        String latlong = generateTransportDistanceTimeGoTo();
        HttpClient client = new HttpClient(this, Constans.builUrl(latlong, problem.getApiKeyGoogle()));
        if (client.executeRequest()) {
            client.parseObjects(new Distance_Matrix());
        }
    }

    private void executeProblem() {

        try {

            getVehicleImpl();
            getServices();
            buildCostMatrix();
            generateVrpProblem();
            VehicleRoutingAlgorithm vra = Jsprit.createAlgorithm(vrp);
            Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();
            SolutionPrinter.print(vrp, Solutions.bestOf(solutions), SolutionPrinter.Print.VERBOSE);
            VehicleRoutingProblemSolution problemSolution = Solutions.bestOf(solutions);
            solution = getSolution(problemSolution);
            notification.sendSuccess(solution);

        } catch (Exception e) {
            error = e.getMessage();
            notification.sendError(problem, error);
        }

    }

    private List<VehicleImpl> getVehicleImpl() {
        List<VehicleImpl> vehicleImpls = new ArrayList<>();

        for (Vehicle vehicle : problem.getVehicles()) {

            VehicleType type = VehicleTypeImpl.Builder.newInstance(
                    vehicle.getVehicleType().getVehicleTypeId()).
                    addCapacityDimension(
                            0,
                            vehicle.getVehicleType().getCapacity().intValue())
                    .setCostPerDistance(1)
                    .build();

            vehicleImpls.add(
                    VehicleImpl.Builder
                            .newInstance(vehicle.getVehicleId())
                            .setStartLocation(Location.newInstance(problem.getNode_Depot().getLocation().getLocationId()))
                            .setType(type)
                            .setReturnToDepot(true)
                            .build()
            );

        }
        return vehicleImpls;
    }

    private Solution getSolution(VehicleRoutingProblemSolution problemSolution) {

        Solution solution = new Solution();
        solution.setSolutionId("");
        solution.setDate(Utils.getDateCurrent());
        //Enviar Deposito
        solution.setDepots(problem.getNode_Depot());
        List<Rute> rutes = new ArrayList<>();

        int routeNu = 1;
        List<VehicleRoute> list = new ArrayList<VehicleRoute>(problemSolution.getRoutes());
        for (VehicleRoute route : list) {

            Vehicle vehicleRuta = new Vehicle();
            List<Node_Client> clients = new ArrayList<>();
            int secuence = 0;
            TourActivity prevAct = route.getStart();
            for (TourActivity act : route.getActivities()) {
                for (Vehicle vehicle : problem.getVehicles()) {

                    if (vehicle.getVehicleId().equals(route.getVehicle().getId())) {
                        vehicleRuta = vehicle;
                    }
                }

                String jobId;
                if (act instanceof TourActivity.JobActivity) {
                    jobId = ((TourActivity.JobActivity) act).getJob().getId();

                    for (Node_Client client : problem.getClients()) {
                        if (client.getNodeClientId().equals(jobId)) {

                            secuence = secuence + 1;
                            client.setSequence(secuence);
                            clients.add(client);

                        }
                    }
                } else {
                    jobId = "-";
                }

                rutes.add(new Rute("", "", vrp.getActivityCosts().getActivityCost(act, act.getArrTime(), route.getDriver(), route.getVehicle()), vehicleRuta, clients));

                prevAct = act;
            }

            routeNu++;
        }
        if (problem.isIsFinite()) {

            List<Node_Client> unassignedClients = new ArrayList<>();
            if (!problemSolution.getUnassignedJobs().isEmpty()) {
                for (Job j : problemSolution.getUnassignedJobs()) {
                    for (Node_Client client : problem.getClients()) {
                        if (client.getNodeClientId().equals(j.getId())) {
                            unassignedClients.add(client);
                        }
                    }

                }
            }
            solution.setIsRutesCompleted(false);
            solution.setUnassignedClients(unassignedClients);
        }

        solution.setRutes(rutes);
        solution.setIsSuccess(true);
        return solution;

    }

    private List<Service> getServices() {

        List<Service> services = new ArrayList<>();
        int i = 0;
        for (Node_Client node_Client : problem.getClients()) {

            Double startTime = Utils.getDoubleTime(node_Client.getTimeWindow().getStartTime());
            Double endTime = Utils.getDoubleTime(node_Client.getTimeWindow().getEndTime());

            TimeWindow timeWindow = TimeWindow.newInstance(startTime, endTime);

            services.add(
                    Service.Builder.newInstance(node_Client.getNodeClientId())
                            .setTimeWindow(timeWindow)
                            .addSizeDimension(0, node_Client.getQuantityOrder())
                            .setLocation(Location.newInstance(node_Client.getLocation().getLocationId()))
                            .build());
            i = i + 1;

        }
        return services;
    }

    private void generateVrpProblem() {
        VehicleRoutingProblem.FleetSize fleetSize = VehicleRoutingProblem.FleetSize.FINITE;
        if (!problem.isIsFinite()) {
            fleetSize = VehicleRoutingProblem.FleetSize.INFINITE;
        }
        vrp = VehicleRoutingProblem.Builder.newInstance().setFleetSize(fleetSize).setRoutingCost(costMatrixBuilder.build()).addAllJobs(getServices()).addAllVehicles(getVehicleImpl()).build();
    }

    private void buildCostMatrix() {

        if (distance_Matrix != null) {
            System.out.println("STATUS: " + distance_Matrix.getStatus());
            System.out.println("LENGTH: " + distance_Matrix.getRows().length);

            for (int i = 0; i < distance_Matrix.getRows().length; i++) {

                Node_Client client = hashMaNodes.get(i);
                Element[] elements = distance_Matrix.getRows()[i].getElements();
                for (int j = 0; j < elements.length; j++) {
                    //  Element element                 

                    double distance = Double.parseDouble(elements[j].getDistance().getValue());

                    if (distance > 1) {

                        Node_Client clientDes = hashMaNodes.get(j);
                        System.out.println("i: " + i + " , j: " + j);

                        costMatrixBuilder.addTransportDistance(client.getLocation().getLocationId(), clientDes.getLocation().getLocationId(), distance);

                    }

                }

                System.out.println("============================================");
            }

            this.costMatrix = costMatrixBuilder.build();
        }
    }

    @Override
    public void onSuccess(Object object) {
        distance_Matrix = (Distance_Matrix) object;
        executeProblem();
    }

    @Override
    public void onError(String message) {
        error = message;
    }

}

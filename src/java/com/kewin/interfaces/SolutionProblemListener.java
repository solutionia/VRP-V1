/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.interfaces;

import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;

/**
 *
 * @author Kelv
 */
public interface SolutionProblemListener {
    void onSoluctionSuccess(VehicleRoutingProblemSolution routingProblemSolution);
    void onSoluctionError(String message);
}

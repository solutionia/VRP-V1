/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.controller;

import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.solution.route.VehicleRoute;
import com.kewin.core.ProblemSolution;
import com.kewin.entities.Problem;
import com.kewin.entities.Solution;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kelv
 */
public class InterSolutionController {

    private Problem problem;
    private ProblemSolution problemSolution;
    private Solution solution;

    public InterSolutionController(Problem problem) {
        this.problem = problem;
    }

    public void execute() {
        this.problemSolution = new ProblemSolution(problem);
        this.solution = problemSolution.executeProblem();
    }

    public Solution getSolution() {       
     
        return solution;   
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.solutions.kewin.rest.vrp;

import com.ia.solutions.kewin.dimain.core.ProblemSolution;
import com.ia.solutions.kewin.entities.Problem;
import com.ia.solutions.kewin.entities.Solution;
import com.ia.solutions.kewin.rest.solution.SolutionNotification;
import com.ia.solutions.kewin.rest.solution.SolutionNotificationImpl;

/**
 *
 * @author Vaio
 */
public class ServiceRepositoryImpl implements ServiceRepository, SolutionNotification{
    
    private ProblemSolution problemSolution;
    private SolutionNotification notification;
    public ServiceRepositoryImpl (){
       this.problemSolution = new ProblemSolution(this);
       notification = new SolutionNotificationImpl();
    }

    @Override
    public void sendProblem(Problem problem) {
         this.problemSolution.setProblem(problem);
         this.problemSolution.runSolution();
    }

    @Override
    public void sendSuccess(Solution solution) {
        notification.sendSuccess(solution);
    }

    @Override
    public void sendError(Problem problem, String error) {
        notification.sendError(problem, error);
    }
    
 
    
}

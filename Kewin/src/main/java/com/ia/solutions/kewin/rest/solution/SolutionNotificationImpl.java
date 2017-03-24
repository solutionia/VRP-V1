/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.solutions.kewin.rest.solution;

import com.ia.solutions.kewin.entities.Problem;
import com.ia.solutions.kewin.entities.Solution;

/**
 *
 * @author Vaio
 */
public class SolutionNotificationImpl implements SolutionNotification {
    

    public SolutionNotificationImpl() {
        
    }

    @Override
    public void sendSuccess(Solution solution) {
    }

    @Override
    public void sendError(Problem problem, String error) {
    }

}

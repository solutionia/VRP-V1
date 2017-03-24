/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.solution.vrp;

import com.kewin.core.ProblemSolution;
import com.kewin.entities.Problem;
import com.kewin.entities.Solution;

/**
 *
 * @author Vaio
 */
public class SolutionRepositoryImpl implements SolutionRepository {
    
    private ProblemSolution problemSolution;
    public SolutionRepositoryImpl() {
        this.problemSolution = ProblemSolution.newInstance();
    }    
    
    @Override
    public void sendProblem(Problem problem) {
            
        this.problemSolution
                .setProblem(problem)
                .runDistancesMatrix()
                .runBuildServices()
                .runBuildSolution();        
       
    }
    @Override
    public Solution getSolution(){
      return this.problemSolution.getSolution();
    }
    
}

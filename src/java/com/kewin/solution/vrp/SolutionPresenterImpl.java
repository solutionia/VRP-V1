/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.solution.vrp;

import com.kewin.entities.Problem;
import com.kewin.entities.Solution;

/**
 *
 * @author Vaio
 */
public class SolutionPresenterImpl implements SolutionPresenter {

    private Solution solution;
    private SolutionRepository repository;

    public SolutionPresenterImpl() {
        this.repository = new SolutionRepositoryImpl();

    }

    @Override
    public void sendProblem(Problem problem) {
        repository.sendProblem(problem);
        this.solution = repository.getSolution();

    }

    @Override
    public Solution getSolution() {        
        return solution;
    }



}

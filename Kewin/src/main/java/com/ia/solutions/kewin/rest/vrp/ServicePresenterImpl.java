/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.solutions.kewin.rest.vrp;

import com.ia.solutions.kewin.entities.Problem;

/**
 *
 * @author Vaio
 */
public class ServicePresenterImpl implements ServicePresenter {

    private ServiceRepository repository;

    public ServicePresenterImpl() {
        this.repository = new ServiceRepositoryImpl();
    }

    @Override
    public void sendProblem(Problem problem) {
        repository.sendProblem(problem);
    }

}

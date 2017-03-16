/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.runTest;

import com.kewin.entities.Distance_Matrix;
import com.kewin.httpRequest.HttpClient;
import com.kewin.interfaces.HttpClientListener;
import com.kewin.utils.Constans;

/**
 *
 * @author Kelv
 */
public class ExampleTest implements HttpClientListener {

    public void runExample() {
        HttpClient client = new HttpClient(this,Constans.API_KEY);
        if (client.executeRequest()) {
            client.parseObjects(new Distance_Matrix());
        }

    }

    @Override
    public void onSuccess(Object object) {
        Distance_Matrix distanceMatrix = (Distance_Matrix) object;
        System.out.println("Distance:"+distanceMatrix.getStatus());
    }

    @Override
    public void onError(String message) {
        System.err.println("Error: "+message);
    }
}

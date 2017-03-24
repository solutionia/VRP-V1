/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.apiRest;

import com.kewin.entities.Problem;
import com.kewin.entities.Solution;
import com.kewin.solution.vrp.SolutionPresenter;
import com.kewin.solution.vrp.SolutionPresenterImpl;
import com.kewin.utils.Constans;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Kelv
 */
@Path("VRPService")
public class VRPService {    
   
    
    @POST
    @Produces(Constans.MEDYA_TYPE_APPLICATION_JSON)
    public Solution drawRoutes(Problem problem) {    
       SolutionPresenter presenter = new SolutionPresenterImpl();
       presenter.sendProblem(problem);
       return  presenter.getSolution();
    }
    @Path("HELLO")
    @POST
    public String hello(){
    return "Hola Kelvin";
    }
}

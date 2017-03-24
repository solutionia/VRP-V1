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
public interface SolutionPresenter {
      void sendProblem(Problem problem);
      Solution getSolution();
}

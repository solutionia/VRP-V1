/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.httpRequest;

/**
 *
 * @author Kelv
 */
public interface HttpClientListener {
    
    void onSuccess (Object object);    
    void onError(String message);
    
}

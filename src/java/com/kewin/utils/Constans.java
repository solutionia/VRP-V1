/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.utils;

/**
 *
 * @author Kelv
 */
public class Constans {

    public static final boolean ISSYMETRYC_TRUE = true;
    public static final boolean ISSYMETRYC_FALSE = false;
    public static final String DIRECTORY_LOG = "D:\\Log";
    public static final String REQUEST_DENIED = "REQUEST_DENIED";
    public static final String REQUEST_OK = "OK";
    public static final String API_KEY = "AIzaSyCagC0TKLKuZ_1whR2PFpU4lU9yUmFfwWY";

    public static String builUrl(String latLong, String apiKey) {
        return "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + latLong + "&destinations=" + latLong + "&mode=driving&language=es&key=" + apiKey + "";

    }
    public static final String URL_FIREBASE = "https://kewin-6f6cb.firebaseio.com/";
    public static final String PATH_JSON_GOOGLE_KEY = "C:\\Users\\Vaio\\Documents\\NetBeansProjects\\KEWIN-VRP-SPRIT\\web\\Credentials\\Kewin-d616a9ff424e.json";

    public static final String PATH_ROOT = "KEWIN";
    public static final String PATH_ROOT_SERVICE = "SERVICE";
    
       public static final String PATH_FIREBASE_SOLUTIONS = "SOLUTIONS";
          public static final String PATH_FIREBASE_PROBLEMS = "PROBLEMS";

    //Url 
    public static final String MEDYA_TYPE_APPLICATION_JSON = "application/json; charset=utf-8";

}

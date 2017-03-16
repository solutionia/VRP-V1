/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Kelv
 */
public class Utils {

    public static boolean validateApiKeys(String key) {

        if (key == null || key.equals("")) {
            return false;
        }
        return true;
    }

    public static String getDateCurrent() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
        // System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48

    }

    public static Date getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return date;       // System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48

    }
    
    public static Double getDoubleTime (String time){
      String timestampStr = time;
        String[] tokens = timestampStr.split(":");
        int hours = Integer.parseInt(tokens[0]);
        int minutes = Integer.parseInt(tokens[1]);
        int seconds = Integer.parseInt(tokens[2]);
        Integer duration = hours*60;
        return duration.doubleValue();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.entities;

/**
 *
 * @author Kelv
 */
public class TimeWindow {

    //Formato String
    private String timeWindowId;
    //Formato 24h HH.mm:ss
    private String startTime;
    //Formato 24h HH.mm:ss
    private String endTime;

    public TimeWindow() {
    }

    public TimeWindow(String timeWindowId, String startTime, String endTime) {
        this.timeWindowId = timeWindowId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTimeWindowId() {
        return timeWindowId;
    }

    public void setTimeWindowId(String timeWindowId) {
        this.timeWindowId = timeWindowId;
    }

    public String getStartTime() {

        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {

      
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}

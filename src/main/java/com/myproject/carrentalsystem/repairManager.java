/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

/**
 *
 * @author hicru
 */
class repairManager {
    
    private String vehicle;
    private String type;
    private String description;
    private double cost;
    private String Date;
    private String endDate;
    
    public repairManager(String vehicle, String type, String description,
            double cost, String Date, String endDate){
        this.vehicle = vehicle;
        this.type = type;
        this.description = description;
        this.cost = cost;
        this.Date = Date;
        this.endDate = endDate;
        
    }
    
    public String getVehicle(){
        return vehicle;
    }
    
    public String getType(){
        return type;
    }
    
    public String getDescription(){
        return description;
    }
    
    public double getTotalCost(){
        return cost;
    }
    
    public String getStartDate(){
        return Date;
    }
    
    public String getEndDate(){
        return endDate;
    }
}
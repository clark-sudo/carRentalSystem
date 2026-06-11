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
    
    private String carID;
    private String carParts;
    private String Quantity;
    private double Price;
    private String Date;
    
    public repairManager(String carID, String carParts, String Quantity, double Price,
            String Date){
        this.carID = carID;
        this.carParts = carParts;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Date = Date;
        
    }
    
    public String getCarID(){
        return carID;
    }
    
    public String getCustomerID(){
        return carParts;
    }
    
    public String getCustomerName(){
        return Quantity;
    }
    
    public double getRentFee(){
        return Price;
    }
    
    public String getRentHour(){
        return Date;
    }
}
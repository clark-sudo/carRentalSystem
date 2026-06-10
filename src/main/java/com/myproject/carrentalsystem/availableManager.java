/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

/**
 *
 * @author james
 */
public class CalendarInMemory {
    
    private String carID;
    private String customerID;
    private String customerName;
    private String rentFee;
    private String rentHour;
    private String startDate;
    private String dueDate;
    
    public CalendarInMemory(String carID, String customerID, String customerName, String rentFee,
            String rentHour, String startDate, String dueDate){
        this.carID = carID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.rentFee = rentFee;
        this.rentHour = rentHour;
        this.startDate = startDate;
        this.dueDate = dueDate;
        
    }
    
    public String getCarID(){
        return carID;
    }
    
    public String getCustomerID(){
        return customerID;
    }
    
    public String getCustomerName(){
        return customerName;
    }
    
    public String getRentFee(){
        return rentFee;
    }
    
    public String getRentHour(){
        return rentHour;
    }
    
    public String getStartDate(){
        return startDate;
    }
    
    public String getDueDate(){
        return dueDate;
    }
    
    
}

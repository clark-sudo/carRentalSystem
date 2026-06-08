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
    
    public void setCarID(String carID) {
    this.carID = carID;
    }

    public void setCustomerID(String customerID) {
    this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
    this.customerName = customerName;
    }

    public void setRentFee(String rentFee) {
    this.rentFee = rentFee;
    }

    public void setRentHour(String rentHour) {
    this.rentHour = rentHour;
    }

    public void setStartDate(String startDate) {
    this.startDate = startDate;
    }

    public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
    private String totalPrice;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
    public CalendarInMemory(String carID, String customerID, String customerName, String rentFee,
            String rentHour, String startDate, String dueDate){
        this.carID = carID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.rentFee = rentFee;
        this.rentHour = rentHour;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.totalPrice = computeTotal(rentFee, rentHour, startDate, dueDate);
    }
    
    /*
     * Total Price Logic:
     * - Days are computed from the start date and due date
     * - If rentHour is 12, it adds 0.5 to the day count (half day)
     * - Same day + 12 hours = 0.5 days
     * - Different dates + 12 hours = days + 0.5
     * - Different dates, no extra hours = just days
     * Formula: totalPrice = rentFee x totalDays
     */
    public static String computeTotal(String fee, String hour, String startDateStr, String dueDateStr) {
        try {
            double rentFee = Double.parseDouble(fee);
            LocalDate start = LocalDate.parse(startDateStr, formatter);
            LocalDate due = LocalDate.parse(dueDateStr, formatter);
            long daysBetween = ChronoUnit.DAYS.between(start, due);
            
            double totalDays = daysBetween;
            
            // Add half day if user selected 12 hours
            if (hour != null && hour.trim().equals("12")) {
                totalDays += 0.5;
            }
            
            return String.format("%.2f", rentFee * totalDays);
            
        } catch (Exception e) {
            return "N/A";
        }
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
    
    public String getTotalPrice(){
        return totalPrice;
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
        this.totalPrice = computeTotal(this.rentFee, this.rentHour, this.startDate, this.dueDate);
    }
    
    public void setRentHour(String rentHour) {
        this.rentHour = rentHour;
        this.totalPrice = computeTotal(this.rentFee, this.rentHour, this.startDate, this.dueDate);
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
        this.totalPrice = computeTotal(this.rentFee, this.rentHour, this.startDate, this.dueDate);
    }
    
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
        this.totalPrice = computeTotal(this.rentFee, this.rentHour, this.startDate, this.dueDate);
    }
    
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

/**
 *
 * @author james
 */
class customerManager {
    
    private String customerID;
    private String customerName;
    private String contactNumber;
    private String address;
    private String emailAddress;
    
    public customerManager(String customerID,
            String customerName,
            String contactNumber,
            String address,
            String emailAddress){
        this.customerID = customerID;
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        
    }
    
    public String getCustomerID(){
        return customerID;
    }
    
    public String getCustomerName(){
        return customerName;
    }
    
    public String getCustomerNumber(){
        return contactNumber;
    }
    
    public String getAddress(){
        return address;
    }
    
    public String getEmailAddress(){
        return emailAddress;
    }
}
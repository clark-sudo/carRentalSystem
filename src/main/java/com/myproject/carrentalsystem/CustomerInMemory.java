/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

/**
 *
 * @author james
 */

public class CustomerInMemory {

    private String customerID;
    private String customerName;
    private String address;
    private String number;
    private String email;

    public CustomerInMemory(
            String customerID,
            String customerName,
            String address,
            String number,
            String email) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.number = number;
        this.email = email;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
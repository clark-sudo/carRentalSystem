/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

/**
 *
 * @author hicru
 */
public class rentalCars {
    
    private int hourRent;
    private String carModel;
    private double rentalPrice;

    public rentalCars(int hourRent, String carModel, float rentalPrice) {
        this.hourRent = hourRent;
        this.carModel = carModel;
        this.rentalPrice = rentalPrice;
    }
    
    public int getHourRent() {
        return hourRent;
    }

    public String getCarModel() {
        return carModel;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setHourRent(int hourRent) {
        this.hourRent = hourRent;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
}


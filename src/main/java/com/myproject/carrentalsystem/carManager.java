/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

/**
 *
 * @author Sophia
 */
public class carManager {
    private String carRegNo;
    private String carMake;
    private String carModel;
    private double rentalPrice;
    private String available;

    public carManager(String carRegNo, String carMake, String carModel, double rentalPrice, String available) {
        this.carRegNo = carRegNo;
        this.carMake = carMake;
        this.carModel = carModel;
        this.rentalPrice = rentalPrice;
        this.available = available;
    }

    public String getCarRegNo() { 
        return carRegNo; 
    }
    public String getCarMake() { 
        return carMake; 
    }
    public String getCarModel() { 
        return carModel; 
    }
    public double getRentalPrice() { 
        return rentalPrice; 
    }
    public String getAvailable() { 
        return available; 
    }

    public void setCarRegNo(String carRegNo) { 
        this.carRegNo = carRegNo; 
    }
    public void setCarMake(String carMake) { 
        this.carMake = carMake; 
    }
    public void setCarModel(String carModel) { 
        this.carModel = carModel; 
    }
    public void setRentalPrice(float rentalPrice) { 
        this.rentalPrice = rentalPrice; 
    }
    public void setAvailable(String available) { 
        this.available = available; 
    }
}

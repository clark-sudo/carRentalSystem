package com.myproject.carrentalsystem;

public class CarInMemory {

    private String carID;
    private String make;
    private String model;
    private String rentalPrice;
    private String availability;

    public CarInMemory(String carID,
                       String make,
                       String model,
                       String rentalPrice,
                       String availability) {

        this.carID = carID;
        this.make = make;
        this.model = model;
        this.rentalPrice = rentalPrice;
        this.availability = availability;
    }

    public String getCarID() {
        return carID;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getRentalPrice() {
        return rentalPrice;
    }

    public String getAvailability() {
        return availability;
    }
    
    
    public void setCarID(String carID) {
    this.carID = carID;
    }

    public void setMake(String make) {
    this.make = make;
    }

    public void setModel(String model) {
    this.model = model;
    }

    public void setRentalPrice(String rentalPrice) {
    this.rentalPrice = rentalPrice;
    }

    public void setAvailability(String availability) {
    this.availability = availability;
    }

}
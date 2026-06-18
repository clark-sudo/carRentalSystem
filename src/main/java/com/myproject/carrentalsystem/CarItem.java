package com.myproject.carrentalsystem;
public class CarItem {
    private int carId;
    private String make;
    private String model;
    private String color;

    public CarItem(int carId, String make, String model, String color) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.color = color;
    }

    public int getCarId() { return carId; }
    
    @Override
    public String toString() {
        // This is exactly what the user will see in your dropdown combo boxes
        return color + " - " + make + " - " + model;
    }
}
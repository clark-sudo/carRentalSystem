package com.myproject.carrentalsystem;
public class CarItem {
    private int carId;
    private String make;
    private String model;
    private String color;
    private double rentalPrice;
    private String available; // "Available", "Rented", "Maintenance"

    public CarItem(int carId, String make, String model, String color, double rentalPrice, String available) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.color = color;
        this.rentalPrice = rentalPrice;
        this.available = available;
    }

    public int getCarId() { return carId; }
    public double getRentalPrice() { return rentalPrice; }
    public String getAvailable() { return available; }

    @Override
    public String toString() {
        return color + " - " + make + " - " + model;
    }
}
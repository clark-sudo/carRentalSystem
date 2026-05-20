/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.util.ArrayList;

/**
 *
 * @author hicru
 */
public class rentalCarsManager {
    
    
    private ArrayList<rentalCars> carsList = new ArrayList<>();

    public void addCars(rentalCars cars) {
        carsList.add(cars);
    }

    public ArrayList<rentalCars> getAllcars() {
        return carsList;
    }

    public void deleteCars(int index) {
        if (index >= 0 && index < carsList.size()) {
            carsList.remove(index);
        }
    }

    public void updateCars(int index, int hourRent, String carModel, float rentalPrice) {
        if (index >= 0 && index < carsList.size()) {
            rentalCars car = carsList.get(index);
            car.setHourRent(hourRent);
            car.setCarModel(carModel);
            car.setRentalPrice(rentalPrice);
        }
    }
}

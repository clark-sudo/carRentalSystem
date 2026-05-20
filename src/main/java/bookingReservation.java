/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Vivic Zulueta
 */
public class bookingReservation {
    private String carModel;
    private String lessee;
    private String lesseeNumber;

    public bookingReservation(String carModel, String lessee, String lesseeNumber) {
        this.carModel = carModel;
        this.lessee = lessee;
        this.lesseeNumber = lesseeNumber;
    }
    
    public String getCarModel() {
        return carModel;
    }

    public String getLessee() {
        return lessee;
    }

    public String getLesseeNumber() {
        return lesseeNumber;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setLessee(String lessee) {
        this.lessee = lessee;
    }

    public void setLesseeNumber(String lesseeNumber) {
        this.lesseeNumber = lesseeNumber;
    }
}


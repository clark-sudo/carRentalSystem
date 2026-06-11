package zuluetaBranch;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Vivic Zulueta
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 *
 * @author hicru
 */
class bookingReservation {
    
    private String carID;
    private String customerID;
    private String customerName;
    private String rentFee;
    private String rentHour;
    private String startDate;
    private String dueDate;
    
    public bookingReservation(String carID, String customerID, String customerName, String rentFee,
            String rentHour, String startDate, String dueDate){
        this.carID = carID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.rentFee = rentFee;
        this.rentHour = rentHour;
        this.startDate = startDate;
        this.dueDate = dueDate;
        
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
}
//public class bookingReservation {
//    private String carModel;
//    private String lessee;
//    private String lesseeNumber;
//
//    public bookingReservation(String carModel, String lessee, String lesseeNumber) {
//        this.carModel = carModel;
//        this.lessee = lessee;
//        this.lesseeNumber = lesseeNumber;
//    }
//    
//    public String getCarModel() {
//        return carModel;
//    }
//
//    public String getLessee() {
//        return lessee;
//    }
//
//    public String getLesseeNumber() {
//        return lesseeNumber;
//    }
//
//    public void setCarModel(String carModel) {
//        this.carModel = carModel;
//    }
//
//    public void setLessee(String lessee) {
//        this.lessee = lessee;
//    }
//
//    public void setLesseeNumber(String lesseeNumber) {
//        this.lesseeNumber = lesseeNumber;
//    }
//}
//

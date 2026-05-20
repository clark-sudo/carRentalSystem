/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author hicru
 */
public class errorMessage {//extends JFrame implements ActionListener
    
//    private JLabel lblMessage;
//    private JButton btnLogin;
    
//    errorMessage(){
//        setSize(350, 150);
//        setLayout(null);
//        setLocationRelativeTo(this);
//        
//        lblMessage = new JLabel ("Please enter valid username and password.");
//        lblMessage.setBounds(50, 10, 250, 40);
//        add(lblMessage);
//        
//        btnLogin = new JButton ("OK");
//        btnLogin.setBounds(250, 60, 60, 30);
//        add(btnLogin);
//        
//        btnLogin.addActionListener(this);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == btnLogin) {
//            dispose();
//        }
//    }
//}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Vivic Zulueta
 */
//public class bookingReservation {
    private String carModel;
    private String lessee;
    private String lesseeNumber;

    public errorMessage(String carModel, String lessee, String lesseeNumber) {
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

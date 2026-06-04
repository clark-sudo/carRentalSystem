/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author hicru
 */
public class homePage extends JFrame implements ActionListener{
    
    private JLabel lblHeader;
    private JButton btnCars, btnCustomer, btnAvailable, btnMaintenance, btnLogout;
    private JTable tblDisplay;
    
    homePage() {
        setName("Car Rental");
        getContentPane().setBackground(new Color(45, 52, 54));
        setSize(1370, 730);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblHeader = new JLabel("Car Rental App", SwingConstants.CENTER);
        lblHeader.setForeground(Color.white);
        lblHeader.setBounds(0, 50, 300, 30);
        add(lblHeader);
        
        btnCars = new JButton("Car Registration");
        btnCars.setBackground(new Color(66, 133, 244));
        btnCars.setForeground(Color.white);
        btnCars.setBounds(50, 130, 200, 40);
        add(btnCars);
        
        btnCustomer = new JButton("Customer");
        btnCustomer.setBackground(new Color(66, 133, 244));
        btnCustomer.setForeground(Color.white);
        btnCustomer.setBounds(50, 200, 200, 40);
        add(btnCustomer);
        
        btnAvailable = new JButton("Calendar");
        btnAvailable.setBackground(new Color(66, 133, 244));
        btnAvailable.setForeground(Color.white);
        btnAvailable.setBounds(50, 270, 200, 40);
        add(btnAvailable);
        
        btnMaintenance = new JButton("Car Maintenance");
        btnMaintenance.setBackground(new Color(66, 133, 244));
        btnMaintenance.setForeground(Color.white);
        btnMaintenance.setBounds(50, 340, 200, 40);
        add(btnMaintenance);
        
        btnLogout = new JButton("LogOut");
        btnLogout.setBackground(new Color(66, 133, 244));
        btnLogout.setForeground(Color.white);
        btnLogout.setBounds(50, 410, 200, 40);
        add(btnLogout);
        
        tblDisplay = new JTable();
        tblDisplay.setBackground(new Color(245, 245, 220));
        tblDisplay.setBounds(300, 0, 1070, 700);
        add(tblDisplay);
        
        btnCars.addActionListener(this);
        btnCustomer.addActionListener(this);
        btnAvailable.addActionListener(this);
        btnMaintenance.addActionListener(this);
        btnLogout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogout) {
            dispose();
            loginPage lp = new loginPage();
            lp.setVisible(true);
        } else if (e.getSource() == btnMaintenance) {
            dispose();
            vehicleMaintenance cm = new vehicleMaintenance();
            cm.setVisible(true);
        } else if (e.getSource() == btnAvailable) {
            dispose();
            calendarManagement cal = new calendarManagement();
            cal.setVisible(true);
        } else if (e.getSource() == btnCustomer) {
            dispose();
            bookingReservation ctm = new bookingReservation();
            ctm.setVisible(true);
        } else if (e.getSource() == btnCars) {
            dispose();
            carRentals car = new carRentals();
            car.setVisible(true);
        } 
    }
    
}
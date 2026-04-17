/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

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
    
    homePage() {
        setName("Car Rental");
        setSize(500, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblHeader = new JLabel("Car Rental App", SwingConstants.CENTER);
        lblHeader.setBounds(0, 50, 500, 30);
        add(lblHeader);
        
        btnCars = new JButton("Car Registration");        
        btnCars.setBounds(150, 130, 200, 40);
        add(btnCars);
        
        btnCustomer = new JButton("Customer");        
        btnCustomer.setBounds(150, 200, 200, 40);
        add(btnCustomer);
        
        btnAvailable = new JButton("Calendar");        
        btnAvailable.setBounds(150, 270, 200, 40);
        add(btnAvailable);
        
        btnMaintenance = new JButton("Car Maintenance");        
        btnMaintenance.setBounds(150, 340, 200, 40);
        add(btnMaintenance);
        
        btnLogout = new JButton("LogOut");        
        btnLogout.setBounds(150,410, 200, 40);
        add(btnLogout);
        
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
            carMaintenance cm = new carMaintenance();
            cm.setVisible(true);
        } else if (e.getSource() == btnAvailable) {
            dispose();
            calendarManagement cal = new calendarManagement();
            cal.setVisible(true);
        } else if (e.getSource() == btnCustomer) {
            dispose();
            customerManagement ctm = new customerManagement();
            ctm.setVisible(true);
        } else if (e.getSource() == btnCars) {
            dispose();
            carManagement car = new carManagement();
            car.setVisible(true);
        } 
    }
    
}

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
    protected static final ArrayList<String> screenSizes = new ArrayList<>(){{
        add("Small screen");
        add("Medium screen");
        add("Normal screen");
        }};

    homePage() {
        this("Normal screen"); //Default
    }
    homePage(String screenType) {
        setName("Car Rental");
        
        if (screenType.equals("Small screen")) {
        setSize(400, 600);
        } else if (screenType.equals("Medium screen")) {
        setSize(600, 700);
        } else {
        setSize(700, 600);
        }
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblHeader = new JLabel("Car Rental App", SwingConstants.CENTER);
        lblHeader.setBounds(0, 50, 600, 100);
        add(lblHeader);
        
        btnCars = new JButton("Car Registration");        
        btnCars.setBounds(250, 200, 200, 40);
        add(btnCars);
        
        btnCustomer = new JButton("Customer");        
        btnCustomer.setBounds(250, 270, 200, 40);
        add(btnCustomer);
        
        btnAvailable = new JButton("Rental Cars");        
        btnAvailable.setBounds(250, 340, 200, 40);
        add(btnAvailable);
        
        btnMaintenance = new JButton("Car Maintenance");        
        btnMaintenance.setBounds(250, 410, 200, 40);
        add(btnMaintenance);
        
        btnLogout = new JButton("LogOut");        
        btnLogout.setBounds(250,480, 200, 40);
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
        } else if (e.getSource() == btnAvailable) {
            dispose();
            loginPage lp = new loginPage();
            lp.setVisible(true);
        }
    }
    
}

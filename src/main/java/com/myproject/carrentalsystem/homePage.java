/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author hicru
 */
public class homePage extends JFrame implements ActionListener{
    
    private JLabel lblHeader;
    private JButton btnCars, btnCustomer, btnAvailable, btnMaintenance, btnLogout;
    private JPanel panel;
    private carRentals cars;
    private bookingReservation customers;
    private rentalInvoices availables;
    private vehicleMaintenance repair;
    
    homePage() {
        setTitle("🚗");
        setSize(1370, 730);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblHeader = new JLabel("Car Rental App", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setForeground(Color.white);
        lblHeader.setBounds(0, 50, 300, 30);
        add(lblHeader);
        
        btnCars = new JButton("Car Registration");
        btnCars.setFont(new Font("Arial", Font.BOLD, 15));
        btnCars.setBackground(new Color(66, 133, 244));
        btnCars.setForeground(Color.white);
        btnCars.setBounds(50, 130, 200, 40);
        add(btnCars);
        
        btnCustomer = new JButton("Customer");
        btnCustomer.setFont(new Font("Arial", Font.BOLD, 15));
        btnCustomer.setBackground(new Color(66, 133, 244));
        btnCustomer.setForeground(Color.white);
        btnCustomer.setBounds(50, 200, 200, 40);
        add(btnCustomer);
        
        btnAvailable = new JButton("Available");
        btnAvailable.setFont(new Font("Arial", Font.BOLD, 15));
        btnAvailable.setBackground(new Color(66, 133, 244));
        btnAvailable.setForeground(Color.white);
        btnAvailable.setBounds(50, 270, 200, 40);
        add(btnAvailable);
        
        btnMaintenance = new JButton("Car Maintenance");
        btnMaintenance.setFont(new Font("Arial", Font.BOLD, 15));
        btnMaintenance.setBackground(new Color(66, 133, 244));
        btnMaintenance.setForeground(Color.white);
        btnMaintenance.setBounds(50, 340, 200, 40);
        add(btnMaintenance);
        
        btnLogout = new JButton("LogOut");
        btnLogout.setFont(new Font("Arial", Font.BOLD, 15));
        btnLogout.setBackground(new Color(66, 133, 244));
        btnLogout.setForeground(Color.white);
        btnLogout.setBounds(50, 410, 200, 40);
        add(btnLogout);
        
        panel = new JPanel();
        panel.setBackground(new Color(45, 52, 54));
        panel.setBounds(0, 0, 300, 700);
        add(panel);
        
        cars = new carRentals();
        cars.setBounds(0, 0, 1370, 700);
        add(cars);
        
        customers = new bookingReservation();
        customers.setBounds(0, 0, 1370, 700);
        add(customers);
        
        availables = new rentalInvoices();
        availables.setBounds(0, 0, 1370, 700);
        add(availables);
        
        repair = new vehicleMaintenance();
        repair.setBounds(0, 0, 1370, 700);
        add(repair);
        
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
            availables.setVisible(false);
            customers.setVisible(false);
            cars.setVisible(false);
            repair.showVehicleMaintenance();
//            dispose();
//            carMaintenance cm = new carMaintenance();
            repair.setVisible(true);
        } else if (e.getSource() == btnAvailable) {
            repair.setVisible(false);
            customers.setVisible(false);
            cars.setVisible(false);
            availables.showRentalInvoice();
            availables.setVisible(true);
//=======
            dispose();
            calendarManagement cal = new calendarManagement();
            cal.setVisible(true);
        } else if (e.getSource() == btnCustomer) {
            repair.setVisible(false);
            availables.setVisible(false);
            cars.setVisible(false);
            customers.showBookingReservation();
//            dispose();
//            bookingReservation ctm = new bookingReservation();
            customers.setVisible(true);
        } else if (e.getSource() == btnCars) {
            repair.setVisible(false);
            availables.setVisible(false);
            customers.setVisible(false);
            cars.showCarRentals();
//            dispose();
//            carRentals car = new carRentals();
            cars.setVisible(true);
        } 
    }
    
}
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hicru
 */
public class bookingReservation extends JFrame implements ActionListener{
    
    private JLabel lblApp, lblHeader, lblCustomerID, lblCustomer, lblAddress, lblNumber, lblEmail;
    private JButton btnCars, btnCustomer, btnAvailable, btnMaintenance, btnLogout, btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtCustomerID, txtCustomer, txtNumber, txtEmail;
    private JTextArea txaAddress;
    private JTable tblManagement, tblDisplay;
    private JScrollPane spTable;
    private DefaultTableModel dfltModel;
    protected static final ArrayList<String> darkMode = new ArrayList<>(){{
        add("ON");
        add("OFF");
        }};

    bookingReservation() {
        this("Normal screen");
    }
    
    bookingReservation(String screenType) {
        
        if (screenType.equals("ON")){
        getContentPane().setBackground(new Color(45, 52, 54));
        } else if (screenType.equals("OFF")){
        getContentPane().setBackground(new Color(245, 245, 220));
        } else {
        }
        setName("Booking Reservation");
        getContentPane().setBackground(new Color(45, 52, 54));
        setSize(1370, 730);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblApp = new JLabel("Car Rental App", SwingConstants.CENTER);
        lblApp.setForeground(Color.white);
        lblApp.setBounds(0, 50, 300, 30);
        add(lblApp);
        
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
        
        lblHeader = new JLabel("Customer");
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(350, 50, 100, 30);
        add(lblHeader);
        
        lblCustomerID = new JLabel("Customer ID ");
        lblCustomerID.setForeground(Color.BLUE);
        lblCustomerID.setBounds(400, 130, 100, 40);
        add(lblCustomerID);
        
        lblCustomer = new JLabel("Customer Name ");
        lblCustomer.setForeground(Color.BLUE);
        lblCustomer.setBounds(400, 190, 100, 40);
        add(lblCustomer);
        
        lblAddress = new JLabel("Address ");
        lblAddress.setForeground(Color.BLUE);
        lblAddress.setBounds(400, 250, 100, 40);
        add(lblAddress);
        
        lblNumber = new JLabel("Mobile Number ");
        lblNumber.setForeground(Color.BLUE);
        lblNumber.setBounds(400, 310, 100, 40);
        add(lblNumber);
        
        lblEmail = new JLabel("Email Address ");
        lblEmail.setForeground(Color.BLUE);
        lblEmail.setBounds(400, 370, 100, 40);
        add(lblEmail);
        
        txtCustomerID = new JTextField();
        txtCustomerID.setBounds(550, 130, 200, 40);
        add(txtCustomerID);
        
        txtCustomer = new JTextField();
        txtCustomer.setBounds(550, 190, 200, 40);
        add(txtCustomer);
        
        txaAddress = new JTextArea();
        txaAddress.setBounds(550, 250, 200, 40);
        add(txaAddress);
        
        txtNumber = new JTextField();
        txtNumber.setBounds(550, 310, 200, 40);
        add(txtNumber);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(550, 370, 200, 40);
        add(txtEmail);
        
        btnAdd = new JButton("Add");     
        btnAdd.setBackground(new Color(0, 130, 120));
        btnAdd.setForeground(Color.white);    
        btnAdd.setBounds(450, 440, 100, 40);
        add(btnAdd);
        
        btnEdit = new JButton("Edit");   
        btnEdit.setBackground(new Color(0, 130, 120));
        btnEdit.setForeground(Color.white);     
        btnEdit.setBounds(650, 440, 100, 40);
        add(btnEdit);
        
        btnDelete = new JButton("Delete");  
        btnDelete.setBackground(new Color(0, 130, 120));
        btnDelete.setForeground(Color.white);      
        btnDelete.setBounds(450, 510, 100, 40);
        add(btnDelete);
        
        btnCancel = new JButton("Clear");  
        btnCancel.setBackground(new Color(0, 130, 120));
        btnCancel.setForeground(Color.white);      
        btnCancel.setBounds(650, 510, 100, 40);
        add(btnCancel);
        
        dfltModel = new DefaultTableModel();
        dfltModel.setColumnIdentifiers(new String[] {
            "Customer ID",
            "Customer Name",
            "Contact Details",
            "Customer Address",
            "Email Address"
        });

        tblManagement = new JTable(dfltModel);
        spTable = new JScrollPane(tblManagement);
        spTable.setBackground(new Color(177, 218, 220));
        spTable.setBounds(800, 130, 500, 500);
        add(spTable);
        
        tblDisplay = new JTable();
        tblDisplay.setBackground(new Color(245, 245, 220));
        tblDisplay.setBounds(300, 0, 1070, 700);
        add(tblDisplay);
        
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnCancel.addActionListener(this);
        
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
            rentalInvoices cal = new rentalInvoices();
            cal.setVisible(true);
        } else if (e.getSource() == btnCustomer) {
            dispose();
            bookingReservation ctm = new bookingReservation();
            ctm.setVisible(true);
        } else if (e.getSource() == btnCars) {
            dispose();
            carRentals car = new carRentals();
            car.setVisible(true);
        } else if (e.getSource() == btnCancel) {
            txtCustomerID.setText("");
            txtCustomer.setText("");
            txaAddress.setText("");
            txtNumber.setText("");
            txtEmail.setText("");
            JOptionPane.showMessageDialog(null, "Text Fields Cleared!");
        } else if (e.getSource() == btnDelete) {
            int selectedRow = tblManagement.getSelectedRow();
            if (selectedRow != -1) {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this from table?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                dfltModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Cutomer Deleted Successfully!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                JOptionPane.showMessageDialog(null, "Operation Canceled.");
            }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to remove.");
            }
        } else if (e.getSource() == btnEdit) {
            int selectedRow = tblManagement.getSelectedRow();
            if (selectedRow != -1) {
//                    manager.updateCars(selectedRow, hourRent, carModel, rentalPrice);
                dfltModel.setValueAt(
                        txtCustomerID.getText(), selectedRow, 0 );
                dfltModel.setValueAt(
                        txtCustomer.getText(), selectedRow, 1 );
                dfltModel.setValueAt(
                        txtNumber.getText(), selectedRow, 2 );
                dfltModel.setValueAt (
                        txaAddress.getText(), selectedRow, 3 );
                dfltModel.setValueAt(
                        txtEmail.getText(), selectedRow, 4 );
                JOptionPane.showMessageDialog(null, "Customer Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.");
            }
        } else if (e.getSource() == btnAdd) {
            String carId = txtCustomerID.getText();
            String brandMake = txtCustomer.getText();
            String carModel = txtNumber.getText();
            String rentalPrice = txaAddress.getText();
            String availability = txtEmail.getText();
                    int hourRent = 1;
//                com.myproject.carrentalsystem.rentalCars car = new com.myproject.carrentalsystem.rentalCars(hourRent, carModel, rentalPrice);
            dfltModel.addRow(new Object[]{
                carId,
                brandMake,
                carModel,
                rentalPrice,
                availability
            });
            JOptionPane.showMessageDialog(null, "Customer Added Successfully!");
           txtCustomerID.setText("");
           txtCustomer.setText("");
           txaAddress.setText("");
           txtNumber.setText("");
           txtEmail.setText("");
        }
    }
    
}

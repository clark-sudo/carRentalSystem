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
public class carRentals extends JFrame implements ActionListener{
    
    private JLabel lblApp, lblHeader, lblCarID, lblMake, lblModel, lblAvailable, lblPrice;
    private JButton btnCars, btnCustomer, btnAvailable, btnMaintenance, btnLogout, btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtCarID, txtMake, txtModel, txtPrice;
    private JComboBox<String> cmbAvailable;
    protected static final String[] confirmation = {"Yes", "No"};
    private JTable tblManagement, tblDisplay;
    private JScrollPane spTable;
    private DefaultTableModel dfltModel;
    public static ArrayList<CarInMemory> carList = new ArrayList<>();
    protected static final ArrayList<String> darkMode = new ArrayList<>(){{
        add("ON");
        add("OFF");
        }};

    carRentals() {
        this("Normal screen");
    }
    
    carRentals(String screenType) {
        
        if (screenType.equals("ON")){
        getContentPane().setBackground(new Color(45, 52, 54));
        } else if (screenType.equals("OFF")){
        getContentPane().setBackground(new Color(245, 245, 220));
        } else {
        }
        setName("Car Rental");
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
        
        lblHeader = new JLabel("Car Registration");
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(350, 50, 100, 30);
        add(lblHeader);
        
        lblCarID = new JLabel("Car ID ");
        lblCarID.setForeground(Color.BLUE);
        lblCarID.setBounds(400, 130, 100, 40);
        add(lblCarID);
        
        lblMake = new JLabel("Make ");
        lblMake.setForeground(Color.BLUE);
        lblMake.setBounds(400, 190, 100, 40);
        add(lblMake);
        
        lblModel = new JLabel("Model ");
        lblModel.setForeground(Color.BLUE);
        lblModel.setBounds(400, 250, 100, 40);
        add(lblModel);
        
        lblPrice = new JLabel("Rental Price ");
        lblPrice.setForeground(Color.BLUE);
        lblPrice.setBounds(400, 310, 100, 40);
        add(lblPrice);
        
        lblAvailable = new JLabel("Available ");
        lblAvailable.setForeground(Color.BLUE);
        lblAvailable.setBounds(400, 370, 100, 40);
        add(lblAvailable);
        
        txtCarID = new JTextField("000001");
        txtCarID.setBounds(550, 130, 200, 40);
        add(txtCarID);
        
        txtMake = new JTextField("Ex. Toyota");
        txtMake.setBounds(550, 190, 200, 40);
        add(txtMake);
        
        txtModel = new JTextField("Ex. Innova");
        txtModel.setBounds(550, 250, 200, 40);
        add(txtModel);
        
        txtPrice = new JTextField();
        txtPrice.setBounds(550, 310, 200, 40);
        add(txtPrice);
        
        cmbAvailable = new JComboBox<>(confirmation);
        cmbAvailable.setBounds(550, 370, 200, 40);
        add(cmbAvailable);
        
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
            "CarRegNo",
            "Car Make",
            "Car Model",
            "Rental Price",
            "Available"
        });

        tblManagement = new JTable(dfltModel);
        loadTableData();
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
        } else if (e.getSource() == btnCancel) {
            txtCarID.setText("");
            txtMake.setText("");
            txtModel.setText("");
            txtPrice.setText("");
            JOptionPane.showMessageDialog(null, "Text Fields Cleared!");
        } else if (e.getSource() == btnDelete) {
            int selectedRow = tblManagement.getSelectedRow();
            if (selectedRow != -1) {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this from table?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                carList.remove(selectedRow);
                dfltModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Car Deleted Successfully!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                JOptionPane.showMessageDialog(null, "Operation Canceled.");
            }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to remove.");
            }
        } else if (e.getSource() == btnEdit) {
            String availability = cmbAvailable.getSelectedItem().toString();
            int selectedRow = tblManagement.getSelectedRow();

            if (selectedRow != -1) {
                CarInMemory car = carList.get(selectedRow);

                car.setCarID(txtCarID.getText());
                car.setMake(txtMake.getText());
                car.setModel(txtModel.getText());
                car.setRentalPrice(txtPrice.getText());
                car.setAvailability(availability);

                dfltModel.setValueAt(
                        txtCarID.getText(), selectedRow, 0 );
                dfltModel.setValueAt(
                        txtMake.getText(), selectedRow, 1 );
                dfltModel.setValueAt(
                        txtModel.getText(), selectedRow, 2 );
                dfltModel.setValueAt (
                        txtPrice.getText(), selectedRow, 3 );
                dfltModel.setValueAt(
                        availability, selectedRow, 4 );
                JOptionPane.showMessageDialog(null, "Car Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.");
            }
        } else if (e.getSource() == btnAdd) {
            String carId = txtCarID.getText();
            String brandMake = txtMake.getText();
            String carModel = txtModel.getText();
            String rentalPrice = txtPrice.getText();
            String availability = cmbAvailable.getSelectedItem().toString();
            
            CarInMemory car = new CarInMemory(
                carId,
                brandMake,
                carModel,
                rentalPrice,
                availability
            );

            carList.add(car);
            System.out.println("Cars Stored: " + carList.size());
            int hourRent = 1;
//                com.myproject.carrentalsystem.rentalCars car = new com.myproject.carrentalsystem.rentalCars(hourRent, carModel, rentalPrice);
            dfltModel.addRow(new Object[]{
                carId,
                brandMake,
                carModel,
                rentalPrice,
                availability
            });
            JOptionPane.showMessageDialog(null, "Car Added Successfully!");
            txtCarID.setText("");
            txtMake.setText("");
            txtModel.setText("");
            txtPrice.setText("");
        }
    }
    
    private void loadTableData() {

    dfltModel.setRowCount(0);

    for (CarInMemory car : carList) {

        dfltModel.addRow(new Object[]{
            car.getCarID(),
            car.getMake(),
            car.getModel(),
            car.getRentalPrice(),
            car.getAvailability()
        });
    }
}
}

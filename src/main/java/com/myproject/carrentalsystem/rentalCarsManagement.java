/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Sophia
 */
public class rentalCarsManagement extends JFrame implements ActionListener {
    private JLabel lblHeader, lblHourRent, lblCarModel, lblRentalPrice;
    private JButton btnAdd, btnClear, btnEdit, btnDelete, btnCancel;
    private JTextField txtHourRent, txtCarModel, txtRentalPrice;
    private JTable tblManagement;
    private DefaultTableModel model;
    private JTable tblAvailableCars;
    private DefaultTableModel availableModel;
    
    private rentalCarsManager manager = new rentalCarsManager();
//    private static final String[] columnNames = {"Category", "Car Model", "Rental Price"};
//    private static final String[][] carsData = {
//        {"SUV", "Toyota Innova", "1500"},
//        {"SUV", "Toyota Rush", "1400"},
//        {"SUV", "Toyota Veloz", "1300"},
//        {"SUV", "Honda BRV", "1350"},
//        {"SUV", "Toyota Avanza", "1200"},
//
//        {"SEDAN", "Toyota Vios", "1000"},
//        {"SEDAN", "Mitsubishi Mirage", "900"},
//        {"SEDAN", "Toyota Wigo", "850"},
//        {"SEDAN", "Suzuki Espresso", "800"},
//
//        {"VAN", "Nissan NV350", "2500"}};
    
    rentalCarsManagement() {
        setSize(1000, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblHeader = new JLabel("Rental Cars Management");
        lblHeader.setBounds(50, 50, 200, 30);
        add(lblHeader);

        lblHourRent = new JLabel("Hour Rent");
        lblHourRent.setBounds(100, 130, 100, 40);
        add(lblHourRent);

        lblCarModel = new JLabel("Car Model");
        lblCarModel.setBounds(100, 190, 100, 40);
        add(lblCarModel);

        lblRentalPrice = new JLabel("Rental Price");
        lblRentalPrice.setBounds(100, 250, 100, 40);
        add(lblRentalPrice);

        txtHourRent = new JTextField();
        txtHourRent.setBounds(250, 130, 200, 40);
        add(txtHourRent);

        txtCarModel = new JTextField();
        txtCarModel.setBounds(250, 190, 200, 40);
        add(txtCarModel);

        txtRentalPrice = new JTextField();
        txtRentalPrice.setBounds(250, 250, 200, 40);
        add(txtRentalPrice);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(100, 350, 100, 40);
        add(btnAdd);

        btnClear = new JButton("Clear");
        btnClear.setBounds(250, 350, 100, 40);
        add(btnClear);

        btnEdit = new JButton("Edit");
        btnEdit.setBounds(100, 430, 100, 40);
        add(btnEdit);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(250, 430, 100, 40);
        add(btnDelete);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(175, 500, 100, 40);
        add(btnCancel);

        model = new DefaultTableModel();
        model.addColumn("Hour Rent");
        model.addColumn("Car Model");
        model.addColumn("Rental Price");

        tblManagement = new JTable(model);
        JScrollPane sp1 = new JScrollPane(tblManagement);
        sp1.setBounds(500, 50, 400, 180);
        add(sp1);

        availableModel = new DefaultTableModel();
        availableModel.addColumn("Category");
        availableModel.addColumn("Car Model");
        availableModel.addColumn("Rental Price");

        availableModel.addRow(new Object[]{"SUV", "Toyota Innova", "300"});
        availableModel.addRow(new Object[]{"SUV", "Toyota Rush", "250"});
        availableModel.addRow(new Object[]{"SUV", "Toyota Veloz", "300"});
        availableModel.addRow(new Object[]{"SUV", "Honda BRV", "320"});
        availableModel.addRow(new Object[]{"SUV", "Toyota Avanza", "250"});

        availableModel.addRow(new Object[]{"SEDAN", "Toyota Vios", "180"});
        availableModel.addRow(new Object[]{"SEDAN", "Mitsubishi Mirage", "160"});
        availableModel.addRow(new Object[]{"SEDAN", "Toyota Wigo", "150"});
        availableModel.addRow(new Object[]{"SEDAN", "Suzuki Espresso", "150"});

        availableModel.addRow(new Object[]{"VAN", "Nissan NV350", "400"});

        tblAvailableCars = new JTable(availableModel);
        JScrollPane sp2 = new JScrollPane(tblAvailableCars);
        sp2.setBounds(500, 300, 400, 180);
        add(sp2);

        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnCancel.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            try{
                int hourRent = Integer.parseInt(txtHourRent.getText());
                String carModel = txtCarModel.getText();
                float rentalPrice = Float.parseFloat(txtRentalPrice.getText());
            
                float totalPrice = hourRent * rentalPrice;
            
                rentalCars car = new rentalCars(hourRent, carModel, rentalPrice);
            
                manager.addCars(car);
            
                model.addRow(new Object[]{hourRent, carModel, totalPrice});
            
                JOptionPane.showMessageDialog(null, "Car Added Successfully!");
                txtHourRent.setText("");
                txtCarModel.setText("");
                txtRentalPrice.setText("");
            }
            catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Hour Rent and Rental Price must be numbers only!");
            }
        }
        else if (e.getSource() == btnClear) {
            txtHourRent.setText("");
            txtCarModel.setText("");
            txtRentalPrice.setText("");
            JOptionPane.showMessageDialog(null, "Text Fields Cleared!");
        }
        else if (e.getSource() == btnEdit) {
            int selectedRow = tblManagement.getSelectedRow();
            if (selectedRow != -1) {
                try{
                    int hourRent = Integer.parseInt(txtHourRent.getText());
                    String carModel = txtCarModel.getText();
                    float rentalPrice = Float.parseFloat(txtRentalPrice.getText());
                
                    float totalPrice = hourRent * rentalPrice;
                
                    manager.updateCars(selectedRow, hourRent, carModel, rentalPrice);
                
                    model.setValueAt(hourRent, selectedRow, 0);
                    model.setValueAt(carModel, selectedRow, 1);
                    model.setValueAt(totalPrice, selectedRow, 2);
                
                    JOptionPane.showMessageDialog(null, "Car Updated Successfully!");
                }
                catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Hour Rent and Rental Price must be numbers only!");
                }
            }
        }
        else if (e.getSource() == btnDelete) {
            int selectedRow = tblManagement.getSelectedRow();
            if (selectedRow != -1) {
                manager.deleteCars(selectedRow);
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Car Deleted Successfully!");
            }
        }
        else if (e.getSource() == btnCancel) {
            dispose();
            homePage hp = new homePage();
            hp.setVisible(true);
        }
    }
}
//try catch also catches if the textfileds are blank. Fix it me.

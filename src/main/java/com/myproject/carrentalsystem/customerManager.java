/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 *
 * @author hicru
 */
class JDateChooser {
    
    private String carID;
    private String customerID;
    private String customerName;
    private String rentFee;
    private String rentHour;
    private String startDate;
    private String dueDate;
    
    public JDateChooser(String carID, String customerID, String customerName, String rentFee,
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
//    java.util.Date selectedDate = dateChooser.getDate();
//    public class JDateChooser extends JFrame {
//
//    private DefaultTableModel tableModel;
//    private JTable table;

//    /*public*/ JDateChooser() {
//        setTitle("DefaultTableModel Example");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(1000, 700);
//        setLocationRelativeTo(null);
//        setLayout(null);

        // Column names
//        String[] columnNames = {"ID", "Name", "Age"};

        // Initial data
//        Object[][] data = {
//            {1, "Alice", 25},
//            {2, "Bob", 30},
//            {3, "Bob", 30}
//        };

        // Create DefaultTableModel
//        tableModel = new DefaultTableModel(data, columnNames) {
//            // Make cells non-editable (optional)
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };

        // Create JTable with the model
//        table = new JTable(tableModel);
//        JScrollPane scrollPane = new JScrollPane(table);
//
//        // Buttons for actions
//        JButton addButton = new JButton("Add Row");
//        addButton.setBounds(150, 500, 100, 40);
//        addButton.addActionListener((ActionEvent e) -> {
//            tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, "New Person", 20});
//        });

//        JButton removeButton = new JButton("Remove Selected");
//        removeButton.addActionListener((ActionEvent e) -> {
//            int selectedRow = table.getSelectedRow();
//            if (selectedRow != -1) {
//                tableModel.removeRow(selectedRow);
//            } else {
//                JOptionPane.showMessageDialog(this, "Please select a row to remove.");
//            }
//        });

//        JButton updateButton = new JButton("Update First Row");
//        updateButton.addActionListener((ActionEvent e) -> {
//            if (tableModel.getRowCount() > 0) {
//                tableModel.setValueAt("Updated Name", 0, 1); // Row 0, Column 1
//                tableModel.setValueAt(99, 0, 2); // Row 0, Column 2
//            }
//        });

        // Layout
//        JPanel buttonPanel = new JPanel();
//        /*buttonPanel.*/add(addButton);
//        buttonPanel.add(removeButton);
//        buttonPanel.add(updateButton);

        
//        table.setBounds(500, 100, 400, 400);
//        add(table);
//        scrollPane.setBounds(500, 100, 400, 400);
//        add(scrollPane, BorderLayout.CENTER);
//        add(buttonPanel, BorderLayout.SOUTH);
//    }

    //public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> {
            //new DefaultTableModelExample().setVisible(true);
        //});
    //}
//}

//}
//package carrentalsystemtest;

/**
 *
 * @author Sophia
 */
//public class rentalCars {
//    private int hourRent;
//    private String carModel;
//    private double rentalPrice;
//
//    public rentalCars(int hourRent, String carModel, double rentalPrice) {
//        this.hourRent = hourRent;
//        this.carModel = carModel;
//        this.rentalPrice = rentalPrice;
//    }
//    
//    public int getHourRent() {
//        return hourRent;
//    }
//
//    public String getCarModel() {
//        return carModel;
//    }
//
//    public double getRentalPrice() {
//        return rentalPrice;
//    }
//
//    public void setHourRent(int hourRent) {
//        this.hourRent = hourRent;
//    }
//
//    public void setCarModel(String carModel) {
//        this.carModel = carModel;
//    }
//
//    public void setRentalPrice(double rentalPrice) {
//        this.rentalPrice = rentalPrice;
//    }
//}
//
////package carrentalsystemtest;
////
////import java.util.ArrayList;
////
/////**
//// *
//// * @author Sophia
//// */
//public class rentalCarsManager {
//    
//    private ArrayList<rentalCars> carsList = new ArrayList<>();
//
//    public void addCars(rentalCars cars) {
//        carsList.add(cars);
//    }
//
//    public ArrayList<rentalCars> getAllcars() {
//        return carsList;
//    }
//
//    public void deleteCars(int index) {
//        if (index >= 0 && index < carsList.size()) {
//            carsList.remove(index);
//        }
//    }
//
//    public void updateCars(int index, int hourRent, String carModel, float rentalPrice) {
//        if (index >= 0 && index < carsList.size()) {
//            rentalCars car = carsList.get(index);
//            car.setHourRent(hourRent);
//            car.setCarModel(carModel);
//            car.setRentalPrice(rentalPrice);
//        }
//    }
//}

//package carrentalsystemtest;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
///**
// *
// * @author Sophia
// */
//public class rentalCarsManagement extends JFrame implements ActionListener {
//    private JLabel lblHeader, lblHourRent, lblCarModel, lblRentalPrice;
//    private JButton btnAdd, btnClear, btnEdit, btnDelete, btnCancel;
//    private JTextField txtHourRent, txtCarModel, txtRentalPrice;
//    private JTable tblManagement;
//    private DefaultTableModel model;
//    private JTable tblAvailableCars;
//    private DefaultTableModel availableModel;
////    private rentalCarsManager manager = new rentalCarsManager();
////    private static final String[] columnNames = {"Category", "Car Model", "Rental Price"};
////    private static final String[][] carsData = {
////        {"SUV", "Toyota Innova", "1500"},
////        {"SUV", "Toyota Rush", "1400"},
////        {"SUV", "Toyota Veloz", "1300"},
////        {"SUV", "Honda BRV", "1350"},
////        {"SUV", "Toyota Avanza", "1200"},
////
////        {"SEDAN", "Toyota Vios", "1000"},
////        {"SEDAN", "Mitsubishi Mirage", "900"},
////        {"SEDAN", "Toyota Wigo", "850"},
////        {"SEDAN", "Suzuki Espresso", "800"},
////
////        {"VAN", "Nissan NV350", "2500"}};
//    
//    rentalCarsManagement() {
//        setSize(1000, 600);
//        setLayout(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        lblHeader = new JLabel("Rental Cars Management");
//        lblHeader.setBounds(50, 50, 200, 30);
//        add(lblHeader);
//
//        lblHourRent = new JLabel("Hour Rent");
//        lblHourRent.setBounds(100, 130, 100, 40);
//        add(lblHourRent);
//
//        lblCarModel = new JLabel("Car Model");
//        lblCarModel.setBounds(100, 190, 100, 40);
//        add(lblCarModel);
//
//        lblRentalPrice = new JLabel("Rental Price");
//        lblRentalPrice.setBounds(100, 250, 100, 40);
//        add(lblRentalPrice);
//
//        txtHourRent = new JTextField();
//        txtHourRent.setBounds(250, 130, 200, 40);
//        add(txtHourRent);
//
//        txtCarModel = new JTextField();
//        txtCarModel.setBounds(250, 190, 200, 40);
//        add(txtCarModel);
//
//        txtRentalPrice = new JTextField();
//        txtRentalPrice.setBounds(250, 250, 200, 40);
//        add(txtRentalPrice);
//
//        btnAdd = new JButton("Add");
//        btnAdd.setBounds(100, 350, 100, 40);
//        add(btnAdd);
//
//        btnClear = new JButton("Clear");
//        btnClear.setBounds(250, 350, 100, 40);
//        add(btnClear);
//
//        btnEdit = new JButton("Edit");
//        btnEdit.setBounds(100, 430, 100, 40);
//        add(btnEdit);
//
//        btnDelete = new JButton("Delete");
//        btnDelete.setBounds(250, 430, 100, 40);
//        add(btnDelete);
//
//        btnCancel = new JButton("Cancel");
//        btnCancel.setBounds(175, 500, 100, 40);
//        add(btnCancel);
//
//        model = new DefaultTableModel();
//        model.addColumn("Hour Rent");
//        model.addColumn("Car Model");
//        model.addColumn("Rental Price");
//
//        tblManagement = new JTable(model);
//        JScrollPane sp1 = new JScrollPane(tblManagement);
//        sp1.setBounds(500, 50, 400, 180);
//        add(sp1);
//
//        availableModel = new DefaultTableModel();
//        availableModel.addColumn("Category");
//        availableModel.addColumn("Car Model");
//        availableModel.addColumn("Rental Price");
//
//        availableModel.addRow(new Object[]{"SUV", "Toyota Innova", "1500"});
//        availableModel.addRow(new Object[]{"SUV", "Toyota Rush", "1400"});
//        availableModel.addRow(new Object[]{"SUV", "Toyota Veloz", "1300"});
//        availableModel.addRow(new Object[]{"SUV", "Honda BRV", "1350"});
//        availableModel.addRow(new Object[]{"SUV", "Toyota Avanza", "1200"});
//
//        availableModel.addRow(new Object[]{"SEDAN", "Toyota Vios", "1000"});
//        availableModel.addRow(new Object[]{"SEDAN", "Mitsubishi Mirage", "900"});
//        availableModel.addRow(new Object[]{"SEDAN", "Toyota Wigo", "850"});
//        availableModel.addRow(new Object[]{"SEDAN", "Suzuki Espresso", "800"});
//
//        availableModel.addRow(new Object[]{"VAN", "Nissan NV350", "2500"});
//
//        tblAvailableCars = new JTable(availableModel);
//        JScrollPane sp2 = new JScrollPane(tblAvailableCars);
//        sp2.setBounds(500, 300, 400, 180);
//        add(sp2);
//
//        btnAdd.addActionListener(this);
//        btnClear.addActionListener(this);
//        btnEdit.addActionListener(this);
//        btnDelete.addActionListener(this);
//        btnCancel.addActionListener(this);
//    }
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == btnAdd) {
//            String hourRent = txtHourRent.getText();
//            String carModel = txtCarModel.getText();
//            String rentalPrice = txtRentalPrice.getText();
//            model.addRow(new Object[]{
//                hourRent,
//                carModel,
//                rentalPrice
//            });
//            JOptionPane.showMessageDialog(null, "Car Added Successfully!");
//            txtHourRent.setText("");
//            txtCarModel.setText("");
//            txtRentalPrice.setText("");
//        }
//        else if (e.getSource() == btnClear) {
//            txtHourRent.setText("");
//            txtCarModel.setText("");
//            txtRentalPrice.setText("");
//            JOptionPane.showMessageDialog(null, "Text Fields Cleared!");
//        }
//        else if (e.getSource() == btnEdit) {
//            int selectedRow = tblManagement.getSelectedRow();
//            if (selectedRow != -1) {
//                model.setValueAt(
//                        txtHourRent.getText(),
//                        selectedRow,
//                        0
//                );
//                model.setValueAt(
//                        txtCarModel.getText(),
//                        selectedRow,
//                        1
//                );
//                model.setValueAt(
//                        txtRentalPrice.getText(),
//                        selectedRow,
//                        2
//                );
//                JOptionPane.showMessageDialog(null, "Car Updated Successfully!");
//            }
//        }
//        else if (e.getSource() == btnDelete) {
//            int selectedRow = tblManagement.getSelectedRow();
//            if (selectedRow != -1) {
//                model.removeRow(selectedRow);
//                JOptionPane.showMessageDialog(null, "Car Deleted Successfully!");
//            }
//        }
//        else if (e.getSource() == btnCancel) {
//            dispose();
//            homePage hp = new homePage();
//            hp.setVisible(true);
//        }
//    }
}
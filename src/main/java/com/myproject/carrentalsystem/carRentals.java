/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hicru
 */
public class carRentals extends JPanel implements ActionListener{
    
    private JLabel lblHeader, lblCarID, lblMake, lblModel, lblAvailable, lblPrice;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtCarID, txtMake, txtModel, txtPrice;
    private JComboBox<String> cmbAvailable;
    protected static final String[] confirmation = {"Yes", "No"};
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private static final String[] tblColumns = {
            "CarRegNo",
            "Car Make",
            "Car Model",
            "Rental Price",
            "Available"
        };
    private JPanel panel;
    private static ArrayList<carManager> rentalList = new ArrayList<>();
    
    carRentals() {
        
        setSize(1370, 730);
        setLayout(null);
//        setOpaque(false);
        
        lblHeader = new JLabel("Car Registration");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 16));
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(350, 50, 200, 30);
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
        txtCarID.setBackground(new Color(240, 240, 244));
        txtCarID.setBounds(550, 130, 200, 40);
        add(txtCarID);
        
        txtMake = new JTextField("Ex. Toyota");
        txtMake.setBackground(new Color(240, 240, 244));
        txtMake.setBounds(550, 190, 200, 40);
        add(txtMake);
        
        txtModel = new JTextField("Ex. Innova");
        txtModel.setBackground(new Color(240, 240, 244));
        txtModel.setBounds(550, 250, 200, 40);
        add(txtModel);
        
        txtPrice = new JTextField();
        txtPrice.setBackground(new Color(240, 240, 244));
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
        
        model = new DefaultTableModel(tblColumns, 0);
        table = new JTable(model);
        loadTableData() ;
        scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(177, 218, 220));
        scrollPane.setBounds(800, 130, 500, 500);
        add(scrollPane);
        
        panel = new JPanel();
        panel.setBackground(new Color(245, 245, 220));
        panel.setBounds(300, 0, 1070, 700);
        add(panel);
        
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnCancel.addActionListener(this);

        scrollPane.setVisible(false);
        btnCancel.setVisible(false);
        btnDelete.setVisible(false);
        btnEdit.setVisible(false);
        btnAdd.setVisible(false);
        lblAvailable.setVisible(false);
        lblCarID.setVisible(false);
        lblHeader.setVisible(false);
        lblMake.setVisible(false);
        lblModel.setVisible(false);
        lblPrice.setVisible(false);
        txtPrice.setVisible(false);
        txtModel.setVisible(false);
        txtMake.setVisible(false);
        txtCarID.setVisible(false);
        cmbAvailable.setVisible(false);
    }
    
    public void showCarRentals() {
        scrollPane.setVisible(true);
        btnCancel.setVisible(true);
        btnDelete.setVisible(true);
        btnEdit.setVisible(true);
        btnAdd.setVisible(true);
        lblAvailable.setVisible(true);
        lblCarID.setVisible(true);
        lblHeader.setVisible(true);
        lblMake.setVisible(true);
        lblModel.setVisible(true);
        lblPrice.setVisible(true);
        txtPrice.setVisible(true);
        txtModel.setVisible(true);
        txtMake.setVisible(true);
        txtCarID.setVisible(true);
        cmbAvailable.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            txtCarID.setText("");
            txtMake.setText("");
            txtModel.setText("");
            txtPrice.setText("");
            JOptionPane.showMessageDialog(null, "Text Fields Cleared!");
        } else if (e.getSource() == btnDelete) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this from table?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Car Deleted Successfully!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                JOptionPane.showMessageDialog(null, "Operation Canceled.");
            }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to remove.", "Delete", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnEdit) {
            String availability = cmbAvailable.getSelectedItem().toString();
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.setValueAt(
                        txtCarID.getText(), selectedRow, 0 );
                model.setValueAt(
                        txtMake.getText(), selectedRow, 1 );
                model.setValueAt(
                        txtModel.getText(), selectedRow, 2 );
                model.setValueAt (
                        txtPrice.getText(), selectedRow, 3 );
                model.setValueAt(
                        availability, selectedRow, 4 );
                JOptionPane.showMessageDialog(null, "Car Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.", "Update", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnAdd) {
            String carId = txtCarID.getText();
            String brandMake = txtMake.getText();
            String carModel = txtModel.getText();
            String rentalPrice = txtPrice.getText();
            String availability = cmbAvailable.getSelectedItem().toString();
                double cost = 0.0;
            try {
                cost = Double.parseDouble(rentalPrice);
                        
            if (!(carId.isEmpty() || brandMake.isEmpty() || carModel.isEmpty() || rentalPrice.isEmpty())) {
                
                carManager record = new carManager(
                        carId,
                        brandMake,
                        carModel,
                        cost,
                        availability
                );
                rentalList.add(record);
                
            model.addRow(new Object[]{
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
            } else {
                JOptionPane.showMessageDialog(null, "All fields must be Fullfilled.", "Add", JOptionPane.ERROR_MESSAGE);
            }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric rent Price.", "Add", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }
        private void loadTableData(){
            model.setRowCount(0);
            
            for(carManager record : rentalList){
                model.addRow(new Object[]{
                    record.getCarID(),
                    record.getCustomerID(),
                    record.getCustomerName(),
                    record.getRentFee(),
                    record.getRentHour()
                });
            }
    }
}
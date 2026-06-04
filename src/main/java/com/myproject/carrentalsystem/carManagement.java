/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hicru
 */
public class carManagement extends JFrame implements ActionListener{
    
    private JLabel lblHeader, lblCarID, lblMake, lblModel, lblAvailable, lblPrice;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtCarID, txtMake, txtModel, txtPrice;
    private JComboBox<String> cmbAvailable;
    protected static final String[] confirmation = {"Yes", "No"};
    private JTable tblManagement;
    private DefaultTableModel dfltModel;
    //private com.myproject.carrentalsystem.rentalCarsManager manager = new com.myproject.carrentalsystem.rentalCarsManager();

    carManagement() {
        setName("Car Management");
        getContentPane().setBackground(new Color(245, 245, 220));
        setSize(1200, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblHeader = new JLabel("Car Registration");
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(50, 50, 100, 30);
        add(lblHeader);
        
        lblCarID = new JLabel("Car ID ");
        lblCarID.setForeground(Color.BLUE);
        lblCarID.setBounds(100, 130, 100, 40);
        add(lblCarID);
        
        lblMake = new JLabel("Make ");
        lblMake.setForeground(Color.BLUE);
        lblMake.setBounds(100, 190, 100, 40);
        add(lblMake);
        
        lblModel = new JLabel("Model ");
        lblModel.setForeground(Color.BLUE);
        lblModel.setBounds(100, 250, 100, 40);
        add(lblModel);
        
        lblPrice = new JLabel("Rental Price ");
        lblPrice.setForeground(Color.BLUE);
        lblPrice.setBounds(100, 310, 100, 40);
        add(lblPrice);
        
        lblAvailable = new JLabel("Available ");
        lblAvailable.setForeground(Color.BLUE);
        lblAvailable.setBounds(100, 370, 100, 40);
        add(lblAvailable);
        
        txtCarID = new JTextField("000001");
        txtCarID.setBounds(250, 130, 200, 40);
        add(txtCarID);
        
        txtMake = new JTextField("Ex. Toyota");
        txtMake.setBounds(250, 190, 200, 40);
        add(txtMake);
        
        txtModel = new JTextField("Ex. Innova");
        txtModel.setBounds(250, 250, 200, 40);
        add(txtModel);
        
        txtPrice = new JTextField();
        txtPrice.setBounds(250, 310, 200, 40);
        add(txtPrice);
        
        cmbAvailable = new JComboBox<>(confirmation);
        cmbAvailable.setBounds(250, 370, 200, 40);
        add(cmbAvailable);
        
        btnAdd = new JButton("Add");     
        btnAdd.setBackground(new Color(0, 130, 120));
        btnAdd.setForeground(Color.white);    
        btnAdd.setBounds(150, 440, 100, 40);
        add(btnAdd);
        
        btnEdit = new JButton("Edit");   
        btnEdit.setBackground(new Color(0, 130, 120));
        btnEdit.setForeground(Color.white);     
        btnEdit.setBounds(350, 440, 100, 40);
        add(btnEdit);
        
        btnDelete = new JButton("Delete");  
        btnDelete.setBackground(new Color(0, 130, 120));
        btnDelete.setForeground(Color.white);      
        btnDelete.setBounds(150, 510, 100, 40);
        add(btnDelete);
        
        btnCancel = new JButton("Cancel");  
        btnCancel.setBackground(new Color(0, 130, 120));
        btnCancel.setForeground(Color.white);      
        btnCancel.setBounds(350, 510, 100, 40);
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
        JScrollPane sp1 = new JScrollPane(tblManagement);
        sp1.setBackground(new Color(177, 218, 220));
        sp1.setBounds(500, 100, 600, 500);
        add(sp1);
        
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnCancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
            homePage hp = new homePage();
            hp.setVisible(true);
        } else if (e.getSource() == btnDelete) {
            int selectedRow = tblManagement.getSelectedRow();
            if (selectedRow != -1) {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this from table?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                dfltModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Car Deleted Successfully!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                JOptionPane.showMessageDialog(null, "Operation Canceled.");
            }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to remove.");
            }
            txtCarID.setText("");
            txtMake.setText("");
            txtModel.setText("");
            txtPrice.setText("");
        } else if (e.getSource() == btnEdit) {
            String availability = cmbAvailable.getSelectedItem().toString();
            int selectedRow = tblManagement.getSelectedRow();
            if (selectedRow != -1) {
//                    int hourRent = Integer.parseInt(txtHour.getText());
//            String carModel = txtModel.getText();
//                    float rentalPrice = 1f;
//                    manager.updateCars(selectedRow, hourRent, carModel, rentalPrice);
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
    
}
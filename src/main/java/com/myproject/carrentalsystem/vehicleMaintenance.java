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
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author hicru
 */
public class vehicleMaintenance extends JPanel implements ActionListener{
    
    private JLabel lblHeader, lblCarID, lblCarParts, lblQuantity, lblUnitPrice, lblDate;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtCarParts, txtQuantity, txtUnitPrice, txtDate;
    private JComboBox<String> cmbCarID, cmbType;
//    protected static final String[] confirmation = {"1", "2"};
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    protected static final String[] confirmation = {"TOYOTA Innova", "TOYOTA Rush", "TOYOTA Veloz", "TOYOTA Avanza" , "TOYOTA Vios", "TOYOTA Wigo1", "TOYOTA Wigo2", "HONDA BRV", "MITSUBISHI Mirage", "SUZUKI Espresso", "NISSAN NV350"};
    private JPanel panel;
    private LinkedList<MaintenanceRecord> maintenanceHistory = new LinkedList<>();
    
    vehicleMaintenance() {
        
        setLayout(null);
        
        lblHeader = new JLabel("Repair and Maintenance");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 16));
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(350, 50, 200, 30);
        add(lblHeader);
        
        lblCarID = new JLabel("Car ID ");
        lblCarID.setForeground(Color.BLUE);
        lblCarID.setBounds(400, 130, 100, 40);
        add(lblCarID);
        
        lblCarParts = new JLabel("Type ");
        lblCarParts.setForeground(Color.BLUE);
        lblCarParts.setBounds(400, 190, 100, 40);
        add(lblCarParts);
        
        lblQuantity = new JLabel("Description ");
        lblQuantity.setForeground(Color.BLUE);
        lblQuantity.setBounds(400, 250, 100, 40);
        add(lblQuantity);
        
        lblUnitPrice = new JLabel("Total Cost ");
        lblUnitPrice.setForeground(Color.BLUE);
        lblUnitPrice.setBounds(400, 310, 100, 40);
        add(lblUnitPrice);
        
        lblDate = new JLabel("Date ");
        lblDate.setForeground(Color.BLUE);
        lblDate.setBounds(400, 370, 100, 40);
        add(lblDate);
        
        String[] serviceTypes = {"Routine Checkup", "Interim Car Service Intervals", "Full Car Service Intervals", "Major Car Service Intervals", "Oil Change", "Body Repair", "Others (State in the description)"};
        cmbType = new JComboBox<>(serviceTypes);
        cmbType.setBounds(550, 190, 200, 40);
        add(cmbType);
        
        cmbCarID = new JComboBox<>(confirmation);
        cmbCarID.setBounds(550, 130, 200, 40);
        add(cmbCarID);
        
        txtCarParts = new JTextField();
        txtCarParts.setBackground(new Color(240, 240, 244));
        txtCarParts.setBounds(550, 190, 200, 40);
        add(txtCarParts);
        
        txtQuantity = new JTextField();
        txtQuantity.setBackground(new Color(240, 240, 244));
        txtQuantity.setBounds(550, 250, 200, 40);
        add(txtQuantity);
        
        txtUnitPrice = new JTextField();
        txtUnitPrice.setBackground(new Color(240, 240, 244));
        txtUnitPrice.setBounds(550, 310, 200, 40);
        add(txtUnitPrice);
        
        txtDate = new JTextField();
        txtDate.setBackground(new Color(240, 240, 244));
        txtDate.setBounds(550, 370, 200, 40);
        add(txtDate);
        
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
        
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] {
            "Car ID",
            "Car Parts",
            "Quantity",
            "Total Amount",
            "Date"
        });

        table = new JTable(model);
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
        
        lblUnitPrice.setVisible(false);
        scrollPane.setVisible(false);
        btnCancel.setVisible(false);
        btnDelete.setVisible(false);
        btnEdit.setVisible(false);
        btnAdd.setVisible(false);
        lblCarID.setVisible(false);
        lblDate.setVisible(false);
        lblHeader.setVisible(false);
        lblQuantity.setVisible(false);
        lblCarParts.setVisible(false);
        txtCarParts.setVisible(false);
        txtDate.setVisible(false);
        txtQuantity.setVisible(false);
        cmbCarID.setVisible(false);
        txtUnitPrice.setVisible(false);
        cmbType.setVisible(false);
    }
    
    public void showVehicleMaintenance() {
        lblUnitPrice.setVisible(true);
        scrollPane.setVisible(true);
        btnCancel.setVisible(true);
        btnDelete.setVisible(true);
        btnEdit.setVisible(true);
        btnAdd.setVisible(true);
        lblCarID.setVisible(true);
        lblDate.setVisible(true);
        lblHeader.setVisible(true);
        lblQuantity.setVisible(true);
        lblCarParts.setVisible(true);
        txtCarParts.setVisible(true);
        txtDate.setVisible(true);
        txtQuantity.setVisible(true);
        cmbCarID.setVisible(true);
        txtUnitPrice.setVisible(true);
        cmbType.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == btnCancel) {
            txtCarParts.setText("");
            txtQuantity.setText("");
            txtUnitPrice.setText("");
            txtDate.setText("");
            JOptionPane.showMessageDialog(null, "Text Fields Cleared!");
        } else if (e.getSource() == btnDelete) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this from table?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Car Maintenance Deleted Successfully!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                JOptionPane.showMessageDialog(null, "Operation Canceled.");
            }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to remove.");
            }
        } else if (e.getSource() == btnEdit) {
            String carID = cmbCarID.getSelectedItem().toString();
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.setValueAt(
                        carID, selectedRow, 0 );
                model.setValueAt(
                        txtCarParts.getText(), selectedRow, 1 );
                model.setValueAt(
                        txtQuantity.getText(), selectedRow, 2 );
                model.setValueAt (
                        txtUnitPrice.getText(), selectedRow, 3 );
                model.setValueAt(
                        txtDate.getText(), selectedRow, 4 );
                JOptionPane.showMessageDialog(null, "Car Maintenance Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.");
            }
        } else if (e.getSource() == btnAdd) {
            String carId = cmbCarID.getSelectedItem().toString();
            String type = cmbType.getSelectedItem().toString();
            String brandMake = txtCarParts.getText();
            String desc = txtQuantity.getText();
            String costStr = txtUnitPrice.getText();
            String date = txtDate.getText();
                    int hourRent = 1;

            double cost = 0.0;
            try {
                cost = Double.parseDouble(costStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric cost.");
                return;
            }

            MaintenanceRecord record = new MaintenanceRecord(carId, type, desc, cost, date);
            maintenanceHistory.add(record);
            model.addRow(new Object[]{
                carId,
                brandMake,
                desc,
                cost,
                date
            });
            JOptionPane.showMessageDialog(null, "Car Maintenance Added Successfully!");
            txtCarParts.setText("");
            txtQuantity.setText("");
            txtUnitPrice.setText("");
            txtDate.setText("");
        }
    }
    
}

    class MaintenanceRecord {
    String carId, type, description, date;
    double cost;

    public MaintenanceRecord(String carId, String type, String description, double cost, String date) {
        this.carId = carId;
        this.type = type;
        this.description = description;
        this.cost = cost;
        this.date = date;
        }
    }
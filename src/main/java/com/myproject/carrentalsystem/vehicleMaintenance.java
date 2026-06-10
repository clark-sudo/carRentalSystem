/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hicru
 */
public class vehicleMaintenance extends JPanel implements ActionListener{
    
    private JLabel lblHeader, lblCarID, lblCarParts, lblQuantity, lblUnitPrice, lblDate;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtQuantity, txtUnitPrice, txtDate;
    private JComboBox<String> cmbCarID, cmbType;
    protected static final String[] tblColumns = {
            "Car ID",
            "Maintenance Type",
            "Description",
            "Total Cost",
            "Date"
        };
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    protected static final String[] serviceTypes = {"Routine Checkup", "Interim Car Service Intervals", "Full Car Service Intervals", "Major Car Service Intervals", "Oil Change", "Body Repair", "Others (State in the description)"};
    protected static final String[] confirmation = {"TOYOTA Innova", "TOYOTA Rush", "TOYOTA Veloz", "TOYOTA Avanza" , "TOYOTA Vios", "TOYOTA Wigo1", "TOYOTA Wigo2", "HONDA BRV", "MITSUBISHI Mirage", "SUZUKI Espresso", "NISSAN NV350"};
    private JPanel panel;
    private static ArrayList<repairManager> maintenanceHistory = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
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

        cmbType = new JComboBox<>(serviceTypes);
        cmbType.setBounds(550, 190, 200, 40);
        add(cmbType);
        
        cmbCarID = new JComboBox<>(confirmation);
        cmbCarID.setBounds(550, 130, 200, 40);
        add(cmbCarID);
        
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
        
        model = new DefaultTableModel(tblColumns, 0);
        table = new JTable(model);
        MaintenanceRecord() ;
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
        txtDate.setVisible(true);
        txtQuantity.setVisible(true);
        cmbCarID.setVisible(true);
        txtUnitPrice.setVisible(true);
        cmbType.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == btnCancel) {
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
                JOptionPane.showMessageDialog(null, "Please select a row to remove.", "Delete", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnEdit) {
            String carID = cmbCarID.getSelectedItem().toString();
            String carService = cmbType.getSelectedItem().toString();
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.setValueAt(
                        carID, selectedRow, 0 );
                model.setValueAt(
                        carService, selectedRow, 1 );
                model.setValueAt(
                        txtQuantity.getText(), selectedRow, 2 );
                model.setValueAt (
                        txtUnitPrice.getText(), selectedRow, 3 );
                model.setValueAt(
                        txtDate.getText(), selectedRow, 4 );
                JOptionPane.showMessageDialog(null, "Car Maintenance Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.", "Update", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnAdd) {
            String carId = cmbCarID.getSelectedItem().toString();
            String type = cmbType.getSelectedItem().toString();
            String desc = txtQuantity.getText();
            String costStr = txtUnitPrice.getText();
            String dateStr = txtDate.getText();
            double cost = 0.0;
            try {
                cost = Double.parseDouble(costStr);
                LocalDate date = LocalDate.parse(dateStr, formatter);
                
            if (!(type.isEmpty() || desc.isEmpty() || costStr.isEmpty() )) {
            
            repairManager record = new repairManager(
                    carId, 
                    type, 
                    desc, 
                    cost, 
                    date.format(formatter)
            );
            maintenanceHistory.add(record);
            
            model.addRow(new Object[]{
                carId,
                type,
                desc,
                cost,
                date
            });
            } else {
                JOptionPane.showMessageDialog(null, "All fields must be Fullfilled.", "Add", JOptionPane.ERROR_MESSAGE);
            }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric cost.", "Add", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid date format! please use MM/dd/yyyy.", "Add", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void MaintenanceRecord() {
    model.setRowCount(0);

    for(repairManager record : maintenanceHistory) {
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
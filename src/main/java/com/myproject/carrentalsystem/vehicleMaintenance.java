/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author hicru
 */
public class vehicleMaintenance extends JPanel implements ActionListener{
    
    private JLabel lblHeader, lblCarID, lblCarParts, lblQuantity, lblUnitPrice, lblDate, lblDueDate;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtDescription, txtCost, txtDate, txtDueDate;
    private JComboBox<String> cmbVehicle, cmbType;
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
    private boolean isEditing = false;

    Connection connectToDatabase() {
        try {
            
            String url = "jdbc:mysql://localhost:3306/carrental_db";
            String databaseUser = "root"; 
            String databasePassword = ""; 

            return DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database Connection Failed: " + ex.getMessage());
            return null;
        }
    }
        
    void load() {
        model.setRowCount(0);
        
        Connection conn = connectToDatabase();
        if (conn != null) {
            String sql = "SELECT car_id, maintenance_type, description, cost, date FROM vehicle_maintenance";
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while (rs.next()) {
                    String carId = rs.getString("car_id");
                    String type = rs.getString("maintenance_type");
                    String desc = rs.getString("description");
                    double cost = rs.getDouble("cost");
                    String date = rs.getString("date");
                    
                    model.addRow(new Object[]{carId, type, desc, cost, date});
                }
                
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Failed to load background records: " + ex.getMessage());
            }
        }
    }

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
        
        cmbVehicle = new JComboBox<>(confirmation);
        cmbVehicle.setBounds(550, 130, 200, 40);
        add(cmbVehicle);
        
        txtDescription = new JTextField();
        txtDescription.setBackground(new Color(240, 240, 244));
        txtDescription.setBounds(550, 250, 200, 40);
        add(txtDescription);
        
        txtCost = new JTextField();
        txtCost.setBackground(new Color(240, 240, 244));
        txtCost.setBounds(550, 310, 200, 40);
        add(txtCost);
        
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
        
//<<<<<<< HEAD
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
//=======
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] {
            "Car ID",
            "Maintenance Type",
            "Description",
            "Cost",
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
//>>>>>>> 3b0023037c9f3d4bc341013c8d77751b3b582316
        
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnCancel.addActionListener(this);
        load();
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
        txtDescription.setVisible(false);
        cmbVehicle.setVisible(false);
        txtCost.setVisible(false);
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
        txtDescription.setVisible(true);
        cmbVehicle.setVisible(true);
        txtCost.setVisible(true);
        cmbType.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            txtDescription.setText("");
            txtCost.setText("");
            txtDate.setText("");
            JOptionPane.showMessageDialog(null, "Text Fields Cleared!");
        } else if (e.getSource() == btnDelete) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this from table?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                
                String carID = model.getValueAt(selectedRow, 0).toString();
                String type = model.getValueAt(selectedRow, 1).toString();
                String desc = model.getValueAt(selectedRow, 2).toString();
                String costStr = model.getValueAt(selectedRow, 3).toString();
                String date = model.getValueAt(selectedRow, 4).toString();
                
                Connection conn = connectToDatabase();
                    if (conn != null) {
                        String sql = "DELETE FROM vehicle_maintenance WHERE car_id = ? AND maintenance_type = ? AND description = ? AND cost = ? AND date = ?";
                        try {
                            PreparedStatement pst = conn.prepareStatement(sql);
                            
                            pst.setString(1, carID);
                            pst.setString(2, type);
                            pst.setString(3, desc);
                            pst.setDouble(4, Double.parseDouble(costStr));
                            pst.setString(5, date);
                            
                            pst.executeUpdate();
                            pst.close();
                            conn.close();
                            
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Delete Error. " + ex.getMessage());
                            return;
                        }
                    }
                model.removeRow(selectedRow);
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Car Maintenance Deleted Successfully!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                JOptionPane.showMessageDialog(null, "Operation Canceled.");
            }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to remove.", "Delete", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnEdit) {
            
            int selectedRow = table.getSelectedRow();
            String carID = cmbVehicle.getSelectedItem().toString();
            String carService = cmbType.getSelectedItem().toString();
            if (selectedRow != -1) {
            
            if (!isEditing) {
                    isEditing = true;
                    
                    cmbVehicle.setSelectedItem(
                            model.getValueAt(selectedRow, 0) );
                    cmbType.setSelectedItem(
                            model.getValueAt(selectedRow, 1).toString());
                    txtDescription.setText(
                            model.getValueAt(selectedRow, 2).toString());
                    txtCost.setText(
                            model.getValueAt(selectedRow, 3).toString());
                    txtDate.setText(
                            model.getValueAt(selectedRow, 4).toString());
//                    txtDueDate.setText(
//                            model.getValueAt(selectedRow, 5).toString());
                    
//                    cmbVehicle.setEnabled(true);
//                    cmbType.setEnabled(true);
//                    txtDescription.setEnabled(true);
//                    txtCost.setEnabled(true);
//                    txtDate.setEnabled(true);
//                    txtDueDate.setEnabled(true);
                    btnAdd.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "You can now edit the fields. Click Edit again to save.");
                    
                    } else {
                String oldCarID = model.getValueAt(selectedRow, 0).toString();
                String oldType = model.getValueAt(selectedRow, 1).toString();
                String oldDesc = model.getValueAt(selectedRow, 2).toString();
                String oldCost = model.getValueAt(selectedRow, 3).toString();
                String oldDate = model.getValueAt(selectedRow, 4).toString();

                String newCarID = cmbVehicle.getSelectedItem().toString();
                String newType = cmbType.getSelectedItem().toString();
                String newDesc = txtDescription.getText();
                String newCostStr = txtCost.getText();
                String newDate = txtDate.getText();

                double newCost = 0.0;
                try {
                    newCost = Double.parseDouble(newCostStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric cost.");
                    return;
                }

                Connection conn = connectToDatabase();
                if (conn != null) {
                    String sql = "UPDATE vehicle_maintenance SET car_id = ?, maintenance_type = ?, description = ?, cost = ?, date = ? " +
                                 "WHERE car_id = ? AND maintenance_type = ? AND description = ? AND cost = ? AND date = ?";
                    try {
                        PreparedStatement pst = conn.prepareStatement(sql);
                        
                        pst.setString(1, newCarID);
                        pst.setString(2, newType);
                        pst.setString(3, newDesc);
                        pst.setDouble(4, newCost);
                        pst.setString(5, newDate);
                        
                        pst.setString(6, oldCarID);
                        pst.setString(7, oldType);
                        pst.setString(8, oldDesc);
                        pst.setDouble(9, Double.parseDouble(oldCost));
                        pst.setString(10, oldDate);
                        
                        pst.executeUpdate();
                        pst.close();
                        conn.close();
                        
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Database Update Error: " + ex.getMessage());
                        return; 
                    }
                }

                model.setValueAt(newCarID, selectedRow, 0);   
                model.setValueAt(newType, selectedRow, 1);    
                model.setValueAt(newDesc, selectedRow, 2);     
                model.setValueAt(newCostStr, selectedRow, 3); 
                model.setValueAt(newDate, selectedRow, 4);    
                
                JOptionPane.showMessageDialog(null, "Car Maintenance Updated in Database and Table Successfully!");
                
                txtDescription.setText("");
                txtCost.setText("");
                txtDate.setText("");
            }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.", "Update", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnAdd) {
            String carId = cmbVehicle.getSelectedItem().toString();
            String type = cmbType.getSelectedItem().toString();
            String desc = txtDescription.getText();
            String costStr = txtCost.getText();
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
//<<<<<<< HEAD
            } else {
                JOptionPane.showMessageDialog(null, "All fields must be Fullfilled.", "Add", JOptionPane.ERROR_MESSAGE);
            }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric cost.", "Add", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid date format! please use MM/dd/yyyy.", "Add", JOptionPane.ERROR_MESSAGE);
            }
//=======
            
            Connection conn = connectToDatabase();
            if (conn != null) {
                String sql = "INSERT INTO vehicle_maintenance (car_id, maintenance_type, description, cost, date) VALUES (?, ?, ?, ?, ?)";
                
                try {
                    PreparedStatement pst = conn.prepareStatement(sql);
                    
                    pst.setString(1, carId);
                    pst.setString(2, type);
                    pst.setString(3, desc);
                    pst.setDouble(4, cost);
//                    pst.setString(5, date);
                    
                    pst.executeUpdate();
                    
                    pst.close();
                    conn.close();
                    
                    JOptionPane.showMessageDialog(null, "Car Maintenance Added and Saved to Database Successfully!");
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
                }
            }

            txtDescription.setText("");
            txtCost.setText("");
            txtDate.setText("");
    }
    }
    
//}
//    class MaintenanceRecord {
//    String carId, type, description, date;
//    double cost;
//
//    public MaintenanceRecord(String carId, String type, String description, double cost, String date) {
//        this.carId = carId;
//        this.type = type;
//        this.description = description;
//        this.cost = cost;
//        this.date = date;
////>>>>>>> 3b0023037c9f3d4bc341013c8d77751b3b582316
//        }
//    }
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
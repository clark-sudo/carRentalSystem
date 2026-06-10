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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;

/**
 *
 * @author hicru
 */
public class vehicleMaintenance extends JFrame implements ActionListener{

    private JLabel lblApp, lblHeader, lblCarID, lblType, lblDescription, lblCost, lblDate; 
    private JButton btnCars, btnCustomer, btnAvailable, btnMaintenance, btnLogout, btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtDescription, txtCost, txtDate; 
    private JComboBox<String> cmbCarID, cmbType;
    
    protected static final String[] confirmation = {"TOYOTA Innova", "TOYOTA Rush", "TOYOTA Veloz", "TOYOTA Avanza" , "TOYOTA Vios", "TOYOTA Wigo1", "TOYOTA Wigo2", "HONDA BRV", "MITSUBISHI Mirage", "SUZUKI Espresso", "NISSAN NV350"};
    private JTable tblManagement, tblDisplay;
    private JScrollPane spTable;
    private DefaultTableModel dfltModel;
    protected static final ArrayList<String> darkMode = new ArrayList<>(){{
        add("ON");
        add("OFF");
        }};
    

    private LinkedList<MaintenanceRecord> maintenanceHistory = new LinkedList<>();
    
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

    vehicleMaintenance() {
        this("Normal screen");
    }
    
    vehicleMaintenance(String screenType) {
        
        if (screenType.equals("ON")){
        getContentPane().setBackground(new Color(45, 52, 54));
        } else if (screenType.equals("OFF")){
        getContentPane().setBackground(new Color(245, 245, 220));
        } else {
        }
        setName("Vehicle Repair");
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
        
        lblHeader = new JLabel("Repair and Maintenance");
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(350, 50, 200, 30);
        add(lblHeader);
        
        lblCarID = new JLabel("Car ID ");
        lblCarID.setForeground(Color.BLUE);
        lblCarID.setBounds(400, 130, 100, 40);
        add(lblCarID);
        
        lblType = new JLabel("Type ");
        lblType.setForeground(Color.BLUE);
        lblType.setBounds(400, 190, 100, 40);
        add(lblType);
        
        lblDescription = new JLabel("Description ");
        lblDescription.setForeground(Color.BLUE);
        lblDescription.setBounds(400, 250, 100, 40);
        add(lblDescription);
        
        lblCost = new JLabel("Total Cost ");
        lblCost.setForeground(Color.BLUE);
        lblCost.setBounds(400, 310, 100, 40);
        add(lblCost);
        
        lblDate = new JLabel("Date ");
        lblDate.setForeground(Color.BLUE);
        lblDate.setBounds(400, 370, 100, 40);
        add(lblDate);
        
        cmbCarID = new JComboBox<>(confirmation);
        cmbCarID.setBounds(550, 130, 200, 40);
        add(cmbCarID);
        
        String[] serviceTypes = {"Routine Checkup", "Interim Car Service Intervals", "Full Car Service Intervals", "Major Car Service Intervals", "Oil Change", "Body Repair", "Others (State in the description)"};
        cmbType = new JComboBox<>(serviceTypes);
        cmbType.setBounds(550, 190, 200, 40);
        add(cmbType);
        
        txtDescription = new JTextField();
        txtDescription.setBounds(550, 250, 200, 40);
        add(txtDescription);
        
        txtCost = new JTextField();
        txtCost.setBounds(550, 310, 200, 40);
        add(txtCost);
        
        txtDate = new JTextField();
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
        
        dfltModel = new DefaultTableModel();
        dfltModel.setColumnIdentifiers(new String[] {
            "Car ID",
            "Maintenance Type",
            "Description",
            "Cost",
            "Date"
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
            txtDescription.setText("");
            txtCost.setText("");
            txtDate.setText("");
            JOptionPane.showMessageDialog(null, "Text Fields Cleared!");
        } else if (e.getSource() == btnDelete) {
            int selectedRow = tblManagement.getSelectedRow();
            if (selectedRow != -1) {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this from table?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                dfltModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Car Maintenance Deleted Successfully!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                JOptionPane.showMessageDialog(null, "Operation Canceled.");
            }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to remove.");
            }
        } else if (e.getSource() == btnEdit) {
            String carID = cmbCarID.getSelectedItem().toString();
            int selectedRow = tblManagement.getSelectedRow();
            
            if (selectedRow != -1) {
                String oldCarID = dfltModel.getValueAt(selectedRow, 0).toString();
                String oldType = dfltModel.getValueAt(selectedRow, 1).toString();
                String oldDesc = dfltModel.getValueAt(selectedRow, 2).toString();
                String oldCost = dfltModel.getValueAt(selectedRow, 3).toString();
                String oldDate = dfltModel.getValueAt(selectedRow, 4).toString();

                String newCarID = cmbCarID.getSelectedItem().toString();
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
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        
                        pstmt.setString(1, newCarID);
                        pstmt.setString(2, newType);
                        pstmt.setString(3, newDesc);
                        pstmt.setDouble(4, newCost);
                        pstmt.setString(5, newDate);
                        
                        pstmt.setString(6, oldCarID);
                        pstmt.setString(7, oldType);
                        pstmt.setString(8, oldDesc);
                        pstmt.setDouble(9, Double.parseDouble(oldCost));
                        pstmt.setString(10, oldDate);
                        
                        pstmt.executeUpdate();
                        pstmt.close();
                        conn.close();
                        
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Database Update Error: " + ex.getMessage());
                        return; 
                    }
                }

                dfltModel.setValueAt(newCarID, selectedRow, 0);   
                dfltModel.setValueAt(newType, selectedRow, 1);    
                dfltModel.setValueAt(newDesc, selectedRow, 2);     
                dfltModel.setValueAt(newCostStr, selectedRow, 3); 
                dfltModel.setValueAt(newDate, selectedRow, 4);    
                
                JOptionPane.showMessageDialog(null, "Car Maintenance Updated in Database and Table Successfully!");
                
                txtDescription.setText("");
                txtCost.setText("");
                txtDate.setText("");
                
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.");
            }
        } else if (e.getSource() == btnAdd) {
            String carId = cmbCarID.getSelectedItem().toString();
            String type = cmbType.getSelectedItem().toString();
            String desc = txtDescription.getText();
            String costStr = txtCost.getText();
            String date = txtDate.getText();

            double cost = 0.0;
            try {
                cost = Double.parseDouble(costStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric cost.");
                return;
            }

            MaintenanceRecord record = new MaintenanceRecord(carId, type, desc, cost, date);
            maintenanceHistory.add(record); 

            dfltModel.addRow(new Object[]{
                carId,
                type,
                desc,
                cost,
                date
            });
            
            Connection conn = connectToDatabase();
            if (conn != null) {
                String sql = "INSERT INTO vehicle_maintenance (car_id, maintenance_type, description, cost, date) VALUES (?, ?, ?, ?, ?)";
                
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    
                    pstmt.setString(1, carId);
                    pstmt.setString(2, type);
                    pstmt.setString(3, desc);
                    pstmt.setDouble(4, cost);
                    pstmt.setString(5, date);
                    
                    pstmt.executeUpdate();
                    
                    pstmt.close();
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

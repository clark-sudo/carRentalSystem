package com.myproject.carrentalsystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Perfectly matched with 'vehicle_maintenance' database structure
 * @author hicru
 */
public class vehicleMaintenance extends JFrame implements ActionListener {

    private JLabel lblApp, lblHeader, lblCarID, lblType, lblDescription, lblCost, lblDate, lblDueDate; 
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

    vehicleMaintenance() {
        this("Normal screen");
    }
    
    vehicleMaintenance(String screenType) {
        if (screenType.equals("ON")){
            getContentPane().setBackground(new Color(45, 52, 54));
        } else if (screenType.equals("OFF")){
            getContentPane().setBackground(new Color(245, 245, 220));
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
        
        // Added a hidden index 0 column for ID so SQL operations identify exactly which record to edit/delete
        dfltModel = new DefaultTableModel();
        dfltModel.setColumnIdentifiers(new String[] {
            "ID", "Car ID", "Maintenance Type", "Description", "Total Cost", "Date"
        });

        tblManagement = new JTable(dfltModel);
        
        // Hide the internal "ID" column from displaying directly to users while keeping data tracking alive
        tblManagement.removeColumn(tblManagement.getColumnModel().getColumn(0));

        spTable = new JScrollPane(tblManagement);
        spTable.setBackground(new Color(177, 218, 220));
        spTable.setBounds(800, 130, 500, 500);
        add(spTable);
       
        // Pull database entries onto screen
        loadTableData();

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

    // Load data from 'vehicle_maintenance' table using exact column matches
    private void loadTableData() {
        dfltModel.setRowCount(0); 
        String sql = "SELECT * FROM vehicle_maintenance";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                dfltModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("car_id"),
                    rs.getString("maintenance_type"),
                    rs.getString("description"),
                    rs.getDouble("cost"),
                    rs.getString("date")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading database data: " + ex.getMessage());
        }
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
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this record permanently?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    
                    // Safely targets key row based on accurate primary key ID column value
                    int recordId = (int) dfltModel.getValueAt(selectedRow, 0);
                    String delSql = "DELETE FROM vehicle_maintenance WHERE id=?";
                    
                    try (Connection con = DBConnection.getConnection(); 
                         PreparedStatement pst = con.prepareStatement(delSql)) {
                        pst.setInt(1, recordId);
                        pst.executeUpdate();
                        
                        dfltModel.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(null, "Car Maintenance Deleted Successfully!");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Database deletion failed!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to remove.");
            }
        } else if (e.getSource() == btnEdit) {
            int selectedRow = tblManagement.getSelectedRow();
            if (selectedRow != -1) {
                int recordId = (int) dfltModel.getValueAt(selectedRow, 0);
                
                String newCarID = cmbCarID.getSelectedItem().toString();
                String newType = cmbType.getSelectedItem().toString();
                String newDesc = txtDescription.getText();
                String newCost = txtCost.getText();
                String newDate = txtDate.getText();

                String updateSql = "UPDATE vehicle_maintenance SET car_id=?, maintenance_type=?, description=?, cost=?, date=? WHERE id=?";
                try (Connection con = DBConnection.getConnection(); 
                     PreparedStatement pst = con.prepareStatement(updateSql)) {
                    pst.setString(1, newCarID);
                    pst.setString(2, newType);
                    pst.setString(3, newDesc);
                    pst.setDouble(4, Double.parseDouble(newCost));
                    pst.setString(5, newDate);
                    pst.setInt(6, recordId);
                    pst.executeUpdate();
                    
                    loadTableData();
                    JOptionPane.showMessageDialog(null, "Car Maintenance Updated Successfully!");
                } catch(Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Update failed: " + ex.getMessage());
                }
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

            // Target column parameters match schema properties perfectly
            String insSql = "INSERT INTO vehicle_maintenance (car_id, maintenance_type, description, cost, date) VALUES (?, ?, ?, ?, ?)";
            try (Connection con = DBConnection.getConnection(); 
                 PreparedStatement pst = con.prepareStatement(insSql)) {
                pst.setString(1, carId);
                pst.setString(2, type);
                pst.setString(3, desc);
                pst.setDouble(4, cost);
                pst.setString(5, date);
                pst.executeUpdate();
                
                loadTableData();
                JOptionPane.showMessageDialog(null, "Car Maintenance Added and Saved Successfully!");
                
                txtDescription.setText("");
                txtCost.setText("");
                txtDate.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to save record to database!");
            }
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
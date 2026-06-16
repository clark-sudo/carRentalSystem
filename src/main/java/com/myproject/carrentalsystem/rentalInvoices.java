/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hicru
 */
public class rentalInvoices extends JPanel implements ActionListener{
    
    private JLabel lblHeader, lblVehicle, lblAvailable, lblCustomer, lblRentFee, lblRentHour, lblDate, lblDueDate;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtAvailable, txtRentFee, txtRentHour, txtDate, txtDueDate;
    private JComboBox<String> cmbVehicle, cmbCustomer;
    protected static final String[] cars = {"1", "2"};
    protected static final String[] customers = {"1", "2"};
    private JPanel panel;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private static final String[] tblColumns = {
                "Vehicle",
                "Customer Name",
                "Rental Fee",
                "Rental Hour",
                "Start Date",
                "Due Date"
        };
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static ArrayList<availableManager> rentalList = new ArrayList<>();
    private boolean isEditing = false;
    
    rentalInvoices() {
        
        setLayout(null);
        
        lblHeader = new JLabel("Record");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 16));
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(350, 50, 100, 30);
        add(lblHeader);
        
        lblVehicle = new JLabel("Vehicle ");
        lblVehicle.setForeground(Color.BLUE);
        lblVehicle.setBounds(400, 130, 100, 40);
        add(lblVehicle);
        
        lblAvailable = new JLabel("Available ");
        lblAvailable.setForeground(Color.BLUE);
        lblAvailable.setBounds(400, 190, 100, 40);
        add(lblAvailable);
        
        lblCustomer = new JLabel("Customer Name ");
        lblCustomer.setForeground(Color.BLUE);
        lblCustomer.setBounds(400, 250, 100, 40);
        add(lblCustomer);
        
        lblRentFee = new JLabel("Rental Fee ");
        lblRentFee.setForeground(Color.BLUE);
        lblRentFee.setBounds(400, 310, 100, 40);
        add(lblRentFee);
        
        lblRentHour = new JLabel("Rental Hour ");
        lblRentHour.setForeground(Color.BLUE);
        lblRentHour.setBounds(400, 370, 100, 40);
        add(lblRentHour);
        
        lblDate = new JLabel("Date ");
        lblDate.setForeground(Color.BLUE);
        lblDate.setBounds(400, 430, 100, 40);
        add(lblDate);
        
        lblDueDate = new JLabel("End Date ");
        lblDueDate.setForeground(Color.BLUE);
        lblDueDate.setBounds(400, 490, 100, 40);
        add(lblDueDate);
        
        cmbVehicle = new JComboBox<>(cars);
        cmbVehicle.setBounds(550, 130, 200, 40);
        add(cmbVehicle);
        
        txtAvailable = new JTextField();
        txtAvailable.setBackground(new Color(240, 240, 244));
        txtAvailable.setBounds(550, 190, 200, 40);
        add(txtAvailable);
        txtAvailable.setEditable(false);
        
        cmbCustomer = new JComboBox<>(customers);
        cmbCustomer.setBackground(new Color(240, 240, 244));
        cmbCustomer.setBounds(550, 250, 200, 40);
        add(cmbCustomer);
        
        txtRentFee = new JTextField();
        txtRentFee.setBackground(new Color(240, 240, 244));
        txtRentFee.setBounds(550, 310, 200, 40);
        add(txtRentFee);
//        txtRentFee.setEditable(false);
        
        txtRentHour = new JTextField();
        txtRentHour.setBackground(new Color(240, 240, 244));
        txtRentHour.setBounds(550, 370, 200, 40);
        add(txtRentHour);
        
        txtDate = new JTextField();
        txtDate.setBackground(new Color(240, 240, 244));
        txtDate.setBounds(550, 430, 200, 40);
        add(txtDate);
        
        txtDueDate = new JTextField();
        txtDueDate.setBackground(new Color(240, 240, 244));
        txtDueDate.setBounds(550, 490, 200, 40);
        add(txtDueDate);
        
        btnAdd = new JButton("Add");     
        btnAdd.setBackground(new Color(0, 130, 120));
        btnAdd.setForeground(Color.white);    
        btnAdd.setBounds(450, 560, 100, 40);
        add(btnAdd);
        
        btnEdit = new JButton("Edit");   
        btnEdit.setBackground(new Color(0, 130, 120));
        btnEdit.setForeground(Color.white);     
        btnEdit.setBounds(650, 560, 100, 40);
        add(btnEdit);
        
        btnDelete = new JButton("Delete");  
        btnDelete.setBackground(new Color(0, 130, 120));
        btnDelete.setForeground(Color.white);      
        btnDelete.setBounds(450, 630, 100, 40);
        add(btnDelete);
        
        btnCancel = new JButton("Clear");  
        btnCancel.setBackground(new Color(0, 130, 120));
        btnCancel.setForeground(Color.white);      
        btnCancel.setBounds(650, 630, 100, 40);
        add(btnCancel);
        
        model = new DefaultTableModel(tblColumns, 0);
        table = new JTable(model);
        loadTableData();
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
        lblVehicle.setVisible(false);
        lblCustomer.setVisible(false);
        lblAvailable.setVisible(false);
        lblDate.setVisible(false);
        lblDueDate.setVisible(false);
        lblHeader.setVisible(false);
        lblRentFee.setVisible(false);
        lblRentHour.setVisible(false);
        cmbCustomer.setVisible(false);
        txtAvailable.setVisible(false);
        txtDate.setVisible(false);
        txtDueDate.setVisible(false);
        txtRentFee.setVisible(false);
        txtRentHour.setVisible(false);
        cmbVehicle.setVisible(false);
    }
    
    public void showRentalInvoice() {
        scrollPane.setVisible(true);
        btnCancel.setVisible(true);
        btnDelete.setVisible(true);
        btnEdit.setVisible(true);
        btnAdd.setVisible(true);
        lblVehicle.setVisible(true);
        lblCustomer.setVisible(true);
        lblAvailable.setVisible(true);
        lblDate.setVisible(true);
        lblDueDate.setVisible(true);
        lblHeader.setVisible(true);
        lblRentFee.setVisible(true);
        lblRentHour.setVisible(true);
        cmbCustomer.setVisible(true);
        txtAvailable.setVisible(true);
        txtDate.setVisible(true);
        txtDueDate.setVisible(true);
        txtRentFee.setVisible(true);
        txtRentHour.setVisible(true);
        cmbVehicle.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == btnCancel) {
            txtAvailable.setText("");
            txtRentFee.setText("");
            txtRentHour.setText("");
            txtDate.setText("");
            txtDueDate.setText("");
            JOptionPane.showMessageDialog(null, "Text Fields Cleared!");
        } else if (e.getSource() == btnDelete) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Do you want to remove this from table?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {

                    try {

                        String carID = model.getValueAt(selectedRow, 0).toString();
                        String customerID = model.getValueAt(selectedRow, 1).toString();

                        Connection con = DBConnection.getConnection();

                        String sql = "DELETE FROM rentals WHERE car_id=? AND customer_id=?";

                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.setString(1, carID);
                        pst.setString(2, customerID);

                        pst.executeUpdate();

                        model.removeRow(selectedRow);

                        JOptionPane.showMessageDialog(
                                null,
                                "Record Deleted Successfully!",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE
                        );

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error deleting record!");
                    }
                } else {
                JOptionPane.showMessageDialog(null, "Operation Canceled.");
            }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to remove.", "Delete", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnEdit) {
            String available = txtAvailable.getText();
            String rentFee = txtRentFee.getText();
            String startDateStr = txtDate.getText();
            String dueDateStr = txtDueDate.getText();
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                
                if (!isEditing) {
                    isEditing = true;
                    
                    cmbVehicle.setSelectedItem(
                            model.getValueAt(selectedRow, 0) );
                    cmbCustomer.setSelectedItem(
                            model.getValueAt(selectedRow, 1) );
                    txtRentFee.setText(
                            model.getValueAt(selectedRow, 2).toString());
                    txtRentHour.setText(
                            model.getValueAt(selectedRow, 3).toString());
                    txtDate.setText(
                            model.getValueAt(selectedRow, 4).toString());
                    txtDueDate.setText(
                            model.getValueAt(selectedRow, 5).toString());
                    
//                    txtAvailable.setEnabled(true);
//                    cmbCustomer.setEnabled(true);
//                    txtRentFee.setEnabled(true);
//                    txtRentHour.setEnabled(true);
//                    txtDate.setEnabled(true);
//                    txtDueDate.setEnabled(true);
                    btnAdd.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "You can now edit the fields. Click Edit again to save.");
                    
                    } else {
                    String rentHourInput = txtRentHour.getText().trim();

                    if (!rentHourInput.isEmpty() && !rentHourInput.equals("12")) {
                        JOptionPane.showMessageDialog(this, "Rental Hour must be 12 or empty.", "Update", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    try {
                        String carID = cmbVehicle.getSelectedItem().toString();
                        String customerID = cmbCustomer.getSelectedItem().toString();
                        double fee = 0.0;
                        
                        fee = Double.parseDouble(rentFee);

                        LocalDate startDate = LocalDate.parse(txtDate.getText().trim(), formatter);
                        LocalDate dueDate = LocalDate.parse(txtDueDate.getText().trim(), formatter);

                        if (dueDate.isBefore(startDate)) {
                            JOptionPane.showMessageDialog(this, "Due date cannot be before start date.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        
                        CalendarInMemory updatedCim = new CalendarInMemory(
                                carID,
                                txtAvailable.getText(),
                                customerID,
                                txtRentFee.getText(),
                                rentHourInput,
                                startDate.format(formatter),
                                dueDate.format(formatter)
                        );
                        
                        Connection con = DBConnection.getConnection();
                        
                        String sql = "UPDATE rentals SET customer_id=?, customer_name=?, rental_fee=?, "
                                + "rental_hour=?, start_date=?, due_date=?, total_price=? WHERE car_id=?";

                        PreparedStatement pst = con.prepareStatement(sql);

                        pst.setString(1, carID);
                        pst.setString(2, customerID);
                        pst.setDouble(3, Double.parseDouble(txtRentFee.getText()));
                        
                        if (rentHourInput.isEmpty()) {
                            pst.setNull(4, java.sql.Types.INTEGER);
                        } else {
                            pst.setInt(4, Integer.parseInt(rentHourInput));
                        }

                        pst.setDate(5, java.sql.Date.valueOf(startDate));
                        pst.setDate(6, java.sql.Date.valueOf(dueDate));
                        
                        pst.setString(8, carID);

                        pst.executeUpdate();
                                                
                        model.setValueAt(carID, selectedRow, 0);
                        model.setValueAt(customerID, selectedRow, 1);
                        model.setValueAt(txtRentFee.getText(), selectedRow, 2);
                        model.setValueAt(rentHourInput, selectedRow, 3);
                        model.setValueAt(txtDate.getText(), selectedRow, 4);
                        model.setValueAt(txtDueDate.getText(), selectedRow, 5);
                        
                JOptionPane.showMessageDialog(null, "Transaction Updated Successfully!");
                
                        isEditing = false;
                        table.clearSelection();
                        txtRentFee.setText("");
                        txtRentHour.setText("");
                        txtDate.setText("");
                        txtDueDate.setText("");
                        btnAdd.setEnabled(true);
                } catch(DateTimeParseException ex){
                JOptionPane.showMessageDialog(this, "Invalid date format! please use MM/dd/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric fee.", "Update", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error updating record!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.", "Update", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnAdd) {
            String carId = cmbVehicle.getSelectedItem().toString();
            String customerId = txtAvailable.getText();
            String customerName = cmbCustomer.getSelectedItem().toString();
            String rentFee = txtRentFee.getText();
            String rentHour = txtRentHour.getText();
            String startDateStr = txtDate.getText();
            String dueDateStr = txtDueDate.getText();
            double fee = 0.0;
            
            if (!rentHour.isEmpty() && !rentHour.equals("12")) {
                JOptionPane.showMessageDialog(this, "Rental Hour must be 12 or leave it empty.");
                return;
            }
            
            try{
                fee = Double.parseDouble(rentFee);
                LocalDate startDate = LocalDate.parse(startDateStr, formatter);
                LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);
                
                if(dueDate.isBefore(startDate)){
                    JOptionPane.showMessageDialog(this, "Due date cannot be before start date.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (!(carId.isEmpty() || customerName.isEmpty() || rentFee.isEmpty() )) {
                
                    long daysBetween = ChronoUnit.DAYS.between(startDate, dueDate);
                if (daysBetween == 0 && rentHour.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Same day rental requires 12 hours to be entered.");
                    return;
                }
                
                availableManager record = new availableManager(
                        carId, 
                        customerId, 
                        customerName, 
                        rentFee,
                        rentHour,
                        startDate.format(formatter), 
                        dueDate.format(formatter)
                );
            rentalList.add(record);
            
            model.addRow(new Object[] {
                carId,
                customerName, 
                        rentFee,
                        rentHour,
                        startDate.format(formatter), 
                        dueDate.format(formatter)
            });
                
                String sql = "INSERT INTO rentals (car_id, customer_name, rental_fee, rental_hour, start_date, end_date, customer_id, total_price) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

                    pst.setString(1, carId);
                    pst.setString(2, customerName);
                    pst.setDouble(3, Double.parseDouble(rentFee));

                    if (rentHour.isEmpty()) {
                        pst.setNull(4, java.sql.Types.INTEGER);
                    } else {
                        pst.setInt(4, Integer.parseInt(rentHour));
                    }  
                    
                    pst.setDate(5, java.sql.Date.valueOf(startDate));
                    pst.setDate(6, java.sql.Date.valueOf(dueDate));
                    pst.setString(7, customerName);

                    int rowsInserted = pst.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Rental Saved Successfully to Database!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Database rejected insertion. Check constraints.");
                        return; // Stop UI update if DB write failed
                    }

                    } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
                    return; // Stop UI update if DB write failed
                }

                model.addRow(new Object[]{
                    carId,
                    customerName,
                    rentFee,
                    rentHour,
                    startDate.format(formatter),
                    dueDate.format(formatter)
                });
                
                txtAvailable.setText("");
                txtRentFee.setText("");
                txtRentHour.setText("");
                txtDate.setText("");
                txtDueDate.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "All fields must be Fullfilled.", "Add", JOptionPane.ERROR_MESSAGE);
            }
            } catch(DateTimeParseException ex){
                JOptionPane.showMessageDialog(this, "Invalid date format! please use MM/dd/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric fee.", "Add", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    
}
        private void loadTableData(){
            model.setRowCount(0);
            
        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM rentals";

            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getString("car_id"),
                    rs.getString("customer_id"),
                    rs.getString("customer_name"),
                    rs.getDouble("rental_fee"),
                    rs.getString("rental_hour"),
                    rs.getString("start_date"),
                    rs.getString("due_date"),
                    rs.getDouble("total_price")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCarIDs() {

        cmbVehicle.removeAllItems();

        try {

            Connection con = DBConnection.getConnection();

            String sql
                    = "SELECT car_id FROM cars WHERE available = 'Yes'";

            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                cmbVehicle.addItem(
                        rs.getString("car_id")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerIDs() {

//        cmbAvailable.removeAllItems();

        try {

            Connection con = DBConnection.getConnection();

            String sql
                    = "SELECT customer_id FROM customers";

            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

//                cmbAvailable.addItem(
//                        rs.getString("customer_id")
//                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateFieldStatus() {

        if (isEditing) {
            return;
        }

        String selectedCarID
                = (String) cmbVehicle.getSelectedItem();

        if (selectedCarID == null) {
            txtRentFee.setText("");
            return;
        }

        try {

            Connection con = DBConnection.getConnection();

            String sql
                    = "SELECT rental_price, available "
                    + "FROM cars "
                    + "WHERE car_id = ?";

            PreparedStatement pst
                    = con.prepareStatement(sql);

            pst.setString(1, selectedCarID);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                txtRentFee.setText(
                        rs.getString("rental_price")
                );

                String availability
                        = rs.getString("available");

                boolean available
                        = availability.equalsIgnoreCase("Yes");

//                cmbVehicle.setEnabled(available);
//                cmbCustomer.setEnabled(available);
                txtRentFee.setEnabled(available);
                txtRentHour.setEnabled(available);
                txtDate.setEnabled(available);
                txtDueDate.setEnabled(available);
                btnAdd.setEnabled(available);

                if (!available) {
                    JOptionPane.showMessageDialog(
                            this,
                            "This car is currently unavailable."
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCustomerInfo() {

//        String selectedCustomerID
//                = (String) cmbAvailable.getSelectedItem();

//        if (selectedCustomerID == null) {
//            return;
//        }

        try {

            Connection con = DBConnection.getConnection();

            String sql
                    = "SELECT customer_name "
                    + "FROM customers "
                    + "WHERE customer_id = ?";

            PreparedStatement pst
                    = con.prepareStatement(sql);

//            pst.setString(1, selectedCustomerID);

            ResultSet rs = pst.executeQuery();

//            if (rs.next()) {
//
//                txtCustomer.setText(
//                        rs.getString("customer_name")
//                );
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void MaintenanceRecord() {
    model.setRowCount(0);

    for(availableManager record : rentalList) {
        model.addRow(new Object[]{
                    record.getCarID(),
                    record.getCustomerID(),
                    record.getCustomerName(),
                    record.getRentFee(),
                    record.getRentHour(),
                    record.getStartDate(),
                    record.getDueDate()
        });
    }
    }
}
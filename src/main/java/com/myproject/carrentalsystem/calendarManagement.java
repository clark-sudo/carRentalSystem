/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class calendarManagement extends JFrame implements ActionListener{
    
    private JLabel lblHeader, lblCarID, lblCustomerID, lblCustomer, lblRentFee, lblRentHour, lblDate, lblDueDate;
    private JButton btnAdd, btnUpdate, btnDelete, btnCancel;
    private JTextField txtCustomer, txtRentFee, txtRentHour, txtDate, txtDueDate;
    private JComboBox<String> cmbCarID, cmbCustomerID;
    private JTable tblManagement;
    private JScrollPane spTable;
    private DefaultTableModel model;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static ArrayList<CalendarInMemory> rentalList = new ArrayList<>();
    private boolean isEditing = false;

    calendarManagement() {
        getContentPane().setBackground(new Color(245, 245, 220));
        setSize(1200, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblHeader = new JLabel("Calendar");
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(50, 50, 100, 30);
        add(lblHeader);
        
        lblCarID = new JLabel("Car ID ");
        lblCarID.setForeground(Color.BLUE);
        lblCarID.setBounds(100, 130, 100, 40);
        add(lblCarID);
        
        lblCustomerID = new JLabel("Customer ID ");
        lblCustomerID.setForeground(Color.BLUE);
        lblCustomerID.setBounds(100, 190, 100, 40);
        add(lblCustomerID);
        
        lblCustomer = new JLabel("Customer Name ");
        lblCustomer.setForeground(Color.BLUE);
        lblCustomer.setBounds(100, 250, 100, 40);
        add(lblCustomer);
        
        lblRentFee = new JLabel("Rental Fee ");
        lblRentFee.setForeground(Color.BLUE);
        lblRentFee.setBounds(100, 310, 100, 40);
        add(lblRentFee);
        
        lblRentHour = new JLabel("Rental Hour ");
        lblRentHour.setForeground(Color.BLUE);
        lblRentHour.setBounds(100, 370, 100, 40);
        add(lblRentHour);
        
        lblDate = new JLabel("Date ");
        lblDate.setForeground(Color.BLUE);
        lblDate.setBounds(100, 430, 100, 40);
        add(lblDate);
        
        lblDueDate = new JLabel("Due Date ");
        lblDueDate.setForeground(Color.BLUE);
        lblDueDate.setBounds(100, 490, 100, 40);
        add(lblDueDate);
        
        cmbCarID = new JComboBox<>();
        cmbCarID.setBounds(250, 130, 200, 40);
        add(cmbCarID);
        loadCarIDs();
        
        cmbCustomerID = new JComboBox<>();
        cmbCustomerID.setBounds(250, 190, 200, 40);
        add(cmbCustomerID);
        loadCustomerIDs();
        
        txtCustomer = new JTextField();
        txtCustomer.setEditable(false);
        txtCustomer.setBounds(250, 250, 200, 40);
        add(txtCustomer);
        
        txtRentFee = new JTextField();
        txtRentFee.setEditable(false);
        txtRentFee.setBounds(250, 310, 200, 40);
        add(txtRentFee);
        
        txtRentHour = new JTextField();
        txtRentHour.setBounds(250, 370, 200, 40);
        add(txtRentHour);
        
        txtDate = new JTextField();
        txtDate.setBounds(250, 430, 200, 40);
        add(txtDate);
        
        txtDueDate = new JTextField();
        txtDueDate.setBounds(250, 490, 200, 40);
        add(txtDueDate);
        
        btnAdd = new JButton("Add");        
        btnAdd.setBackground(new Color(0, 130, 120));
        btnAdd.setForeground(Color.white);   
        btnAdd.setBounds(150, 560, 100, 40);
        add(btnAdd);
        
        btnUpdate = new JButton("Edit");     
        btnUpdate.setBackground(new Color(0, 130, 120));
        btnUpdate.setForeground(Color.white);     
        btnUpdate.setBounds(350, 560, 100, 40);
        add(btnUpdate);
        
        btnDelete = new JButton("Delete");  
        btnDelete.setBackground(new Color(0, 130, 120));
        btnDelete.setForeground(Color.white);        
        btnDelete.setBounds(150, 630, 100, 40);
        add(btnDelete);
        
        btnCancel = new JButton("Cancel");        
        btnCancel.setBackground(new Color(0, 130, 120));
        btnCancel.setForeground(Color.white);  
        btnCancel.setBounds(350, 630, 100, 40);
        add(btnCancel);
        
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
            "Car ID", "Customer ID", "Customer Name",
            "Rental Fee", "Rental Hour", "Date", "Due Date", "Total Price"
        });
        
        tblManagement = new JTable(model);
        loadTableData();
        spTable = new JScrollPane(tblManagement);
        spTable.setBounds(500, 100, 650, 500);
        add(spTable);
        
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnCancel.addActionListener(this);
        cmbCarID.addActionListener(this);
        cmbCustomerID.addActionListener(this);
        
        updateFieldStatus();
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
                    // Set car back to available when rental is deleted
                    String deletedCarID = rentalList.get(selectedRow).getCarID();
                    for (CarInMemory car : carRentals.carList) {
                        if (car.getCarID().equals(deletedCarID)) {
                            car.setAvailability("Yes");
                            break;
                        }
                    }
                    rentalList.remove(selectedRow);
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Record Deleted Successfully!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Operation Canceled.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to remove.");
            }
            isEditing = false;
            cmbCustomerID.setSelectedIndex(0);
            txtCustomer.setText("");
            txtRentFee.setText("");
            txtRentHour.setText("");
            txtDate.setText("");
            txtDueDate.setText("");
            btnAdd.setEnabled(true);
            tblManagement.clearSelection();
            updateFieldStatus();
            
        } else if (e.getSource() == btnUpdate) {
            int selectedRow = tblManagement.getSelectedRow();
            if (selectedRow != -1) {
                
                // First click on Edit — populate fields from selected row
                if (!isEditing) {
                    isEditing = true;
                    cmbCarID.setSelectedItem(model.getValueAt(selectedRow, 0));
                    cmbCustomerID.setSelectedItem(model.getValueAt(selectedRow, 1));
                    txtCustomer.setText(model.getValueAt(selectedRow, 2).toString());
                    txtRentFee.setText(model.getValueAt(selectedRow, 3).toString());
                    txtRentHour.setText(model.getValueAt(selectedRow, 4).toString());
                    txtDate.setText(model.getValueAt(selectedRow, 5).toString());
                    txtDueDate.setText(model.getValueAt(selectedRow, 6).toString());
                    // Enable all fields so user can make changes
                    cmbCustomerID.setEnabled(true);
                    txtCustomer.setEnabled(true);
                    txtRentFee.setEnabled(true);
                    txtRentHour.setEnabled(true);
                    txtDate.setEnabled(true);
                    txtDueDate.setEnabled(true);
                    btnAdd.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "You can now edit the fields. Click Edit again to save.");
                    
                } else {
                    // Second click on Edit — save the changes
                    String rentHourInput = txtRentHour.getText().trim();
                    
                    // Rent hour must be 12 or empty, nothing else
                    if (!rentHourInput.isEmpty() && !rentHourInput.equals("12")) {
                        JOptionPane.showMessageDialog(this, "Rental Hour must be 12 or leave it empty.");
                        return;
                    }
                    
                    CalendarInMemory rental = rentalList.get(selectedRow);
                    
                    // If car changed, update availability for old and new car
                    String oldCarID = rental.getCarID();
                    String newCarID = (String) cmbCarID.getSelectedItem();
                    if (!oldCarID.equals(newCarID)) {
                        for (CarInMemory car : carRentals.carList) {
                            if (car.getCarID().equals(oldCarID)) {
                                car.setAvailability("Yes");
                            }
                            if (car.getCarID().equals(newCarID)) {
                                car.setAvailability("No");
                            }
                        }
                    }
                    
                    rental.setCarID(newCarID);
                    rental.setCustomerID(cmbCustomerID.getSelectedItem().toString());
                    rental.setCustomerName(txtCustomer.getText());
                    rental.setRentFee(txtRentFee.getText());
                    rental.setRentHour(rentHourInput);
                    rental.setStartDate(txtDate.getText());
                    rental.setDueDate(txtDueDate.getText());
                    
                    model.setValueAt(newCarID, selectedRow, 0);
                    model.setValueAt(cmbCustomerID.getSelectedItem(), selectedRow, 1);
                    model.setValueAt(txtCustomer.getText(), selectedRow, 2);
                    model.setValueAt(txtRentFee.getText(), selectedRow, 3);
                    model.setValueAt(rentHourInput, selectedRow, 4);
                    model.setValueAt(txtDate.getText(), selectedRow, 5);
                    model.setValueAt(txtDueDate.getText(), selectedRow, 6);
                    model.setValueAt(rental.getTotalPrice(), selectedRow, 7);
                    
                    JOptionPane.showMessageDialog(null, "Record Updated Successfully!");
                    isEditing = false;
                    cmbCustomerID.setSelectedIndex(0);
                    txtCustomer.setText("");
                    txtRentFee.setText("");
                    txtRentHour.setText("");
                    txtDate.setText("");
                    txtDueDate.setText("");
                    btnAdd.setEnabled(true);
                    tblManagement.clearSelection();
                    updateFieldStatus();
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.");
            }
            
        } else if (e.getSource() == btnAdd) {
            String carID = cmbCarID.getSelectedItem().toString();
            String customerID = cmbCustomerID.getSelectedItem().toString();
            String customerName = txtCustomer.getText();
            String rentFee = txtRentFee.getText();
            String rentHour = txtRentHour.getText().trim();
            String startDateStr = txtDate.getText();
            String dueDateStr = txtDueDate.getText();
            
            // Rent hour must be 12 or empty, nothing else
            if (!rentHour.isEmpty() && !rentHour.equals("12")) {
                JOptionPane.showMessageDialog(this, "Rental Hour must be 12 or leave it empty.");
                return;
            }
            
            try {
                LocalDate startDate = LocalDate.parse(startDateStr, formatter);
                LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);
                
                if (dueDate.isBefore(startDate)) {
                    JOptionPane.showMessageDialog(this, "Error: due date cannot be before start date.");
                    return;
                }
                
                // Same day with no hours means nothing to charge
                long daysBetween = ChronoUnit.DAYS.between(startDate, dueDate);
                if (daysBetween == 0 && rentHour.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Same day rental requires 12 hours to be entered.");
                    return;
                }
                
                CalendarInMemory cim = new CalendarInMemory(
                        carID, 
                        customerID, 
                        customerName, 
                        rentFee,
                        rentHour,
                        startDate.format(formatter), 
                        dueDate.format(formatter)
                );
                rentalList.add(cim);
                
                for (CarInMemory car : carRentals.carList) {
                    if (car.getCarID().equals(carID)) {
                        car.setAvailability("No");
                        break;
                    }
                }
                
                model.addRow(new Object[]{
                    carID,
                    customerID,
                    customerName,
                    rentFee,
                    rentHour,
                    startDate.format(formatter),
                    dueDate.format(formatter),
                    cim.getTotalPrice()
                });
                
                cmbCustomerID.setSelectedIndex(0);
                txtCustomer.setText("");
                txtRentFee.setText("");
                txtRentHour.setText("");
                txtDate.setText("");
                txtDueDate.setText("");
                
            } catch(DateTimeParseException ex){
                JOptionPane.showMessageDialog(this, "Invalid date format! Please use MM/dd/yyyy.");
            }
            
        } else if(e.getSource() == cmbCarID){
            if (!isEditing) {
                updateFieldStatus();
            }
        } else if(e.getSource() == cmbCustomerID){
            updateCustomerInfo();
        }
    }
    
    private void loadTableData(){
        model.setRowCount(0);
        for(CalendarInMemory cim : rentalList){
            model.addRow(new Object[]{
                cim.getCarID(),
                cim.getCustomerID(),
                cim.getCustomerName(),
                cim.getRentFee(),
                cim.getRentHour(),
                cim.getStartDate(),
                cim.getDueDate(),
                cim.getTotalPrice()
            });
        }
    }
    
    private void loadCarIDs() {
        cmbCarID.removeAllItems();
        for (CarInMemory car : carRentals.carList) {
            cmbCarID.addItem(car.getCarID());
        }
    }
    
    private void loadCustomerIDs() {
        cmbCustomerID.removeAllItems();
        for (CustomerInMemory customer : bookingReservation.customerList) {
            cmbCustomerID.addItem(customer.getCustomerID());
        }
    }
    
    private void updateFieldStatus() {
        if (isEditing) return;
        
        String selectedCarID = (String) cmbCarID.getSelectedItem();
        if (selectedCarID == null) {
            txtRentFee.setText("");
            return;
        }

        for (CarInMemory car : carRentals.carList) {
            if (car.getCarID().equals(selectedCarID)) {
                txtRentFee.setText(car.getRentalPrice());
                boolean available = car.getAvailability().equalsIgnoreCase("Yes");
                cmbCustomerID.setEnabled(available);
                txtCustomer.setEnabled(available);
                txtRentFee.setEnabled(available);
                txtRentHour.setEnabled(available);
                txtDate.setEnabled(available);
                txtDueDate.setEnabled(available);
                btnAdd.setEnabled(available);
                if (!available) {
                    JOptionPane.showMessageDialog(this, "This car is currently unavailable.");
                }
                break;
            }
        }
    }
    
    private void updateCustomerInfo() {
        String selectedCustomerID = (String) cmbCustomerID.getSelectedItem();
        if (selectedCustomerID == null) {
            return;
        }
        for (CustomerInMemory customer : bookingReservation.customerList) {
            if (customer.getCustomerID().equals(selectedCustomerID)) {
                txtCustomer.setText(customer.getCustomerName());
                break;
            }
        }
    }
    
}
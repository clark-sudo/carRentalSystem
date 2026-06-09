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
public class rentalInvoices extends JPanel implements ActionListener{
    
    private JLabel lblHeader, lblCarID, lblCustomerID, lblCustomer, lblRentFee, lblRentHour, lblDate, lblDueDate;
    private JButton btnCars, btnCustomer, btnAvailable, btnMaintenance, btnLogout, btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtCustomerID, txtCustomer, txtRentFee, txtRentHour, txtDate, txtDueDate;
    private JComboBox<String> cmbCarID;
    protected static final String[] confirmation = {"1", "2"};
    private JPanel panel;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private static final String[] tblColumns = {
                "Car ID",
                "Customer ID",
                "Customer Name",
                "Rental Fee",
                "Rental Hour",
                "Date",
                "Due Date"
        };
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static ArrayList<JDateChooser> rentalList = new ArrayList<>();
    
    rentalInvoices() {
        
        setSize(1370, 730);
        setLayout(null);
        
        lblHeader = new JLabel("Calendar");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 16));
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(350, 50, 100, 30);
        add(lblHeader);
        
        lblCarID = new JLabel("Car ID ");
        lblCarID.setForeground(Color.BLUE);
        lblCarID.setBounds(400, 130, 100, 40);
        add(lblCarID);
        
        lblCustomerID = new JLabel("Customer ID ");
        lblCustomerID.setForeground(Color.BLUE);
        lblCustomerID.setBounds(400, 190, 100, 40);
        add(lblCustomerID);
        
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
        
        lblDueDate = new JLabel("Due Date ");
        lblDueDate.setForeground(Color.BLUE);
        lblDueDate.setBounds(400, 490, 100, 40);
        add(lblDueDate);
        
        cmbCarID = new JComboBox<>(confirmation);
        cmbCarID.setBounds(550, 130, 200, 40);
        add(cmbCarID);
        
        txtCustomerID = new JTextField();
        txtCustomerID.setBackground(new Color(240, 240, 244));
        txtCustomerID.setBounds(550, 190, 200, 40);
        add(txtCustomerID);
        
        txtCustomer = new JTextField();
        txtCustomer.setBackground(new Color(240, 240, 244));
        txtCustomer.setBounds(550, 250, 200, 40);
        add(txtCustomer);
        
        txtRentFee = new JTextField();
        txtRentFee.setBackground(new Color(240, 240, 244));
        txtRentFee.setBounds(550, 310, 200, 40);
        add(txtRentFee);
        
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
        lblCarID.setVisible(false);
        lblCustomer.setVisible(false);
        lblCustomerID.setVisible(false);
        lblDate.setVisible(false);
        lblDueDate.setVisible(false);
        lblHeader.setVisible(false);
        lblRentFee.setVisible(false);
        lblRentHour.setVisible(false);
        txtCustomer.setVisible(false);
        txtCustomerID.setVisible(false);
        txtDate.setVisible(false);
        txtDueDate.setVisible(false);
        txtRentFee.setVisible(false);
        txtRentHour.setVisible(false);
        cmbCarID.setVisible(false);
    }
    
    public void showRentalInvoice() {
        scrollPane.setVisible(true);
        btnCancel.setVisible(true);
        btnDelete.setVisible(true);
        btnEdit.setVisible(true);
        btnAdd.setVisible(true);
        lblCarID.setVisible(true);
        lblCustomer.setVisible(true);
        lblCustomerID.setVisible(true);
        lblDate.setVisible(true);
        lblDueDate.setVisible(true);
        lblHeader.setVisible(true);
        lblRentFee.setVisible(true);
        lblRentHour.setVisible(true);
        txtCustomer.setVisible(true);
        txtCustomerID.setVisible(true);
        txtDate.setVisible(true);
        txtDueDate.setVisible(true);
        txtRentFee.setVisible(true);
        txtRentHour.setVisible(true);
        cmbCarID.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == btnCancel) {
            txtCustomerID.setText("");
            txtCustomer.setText("");
            txtRentFee.setText("");
            txtRentHour.setText("");
            txtDate.setText("");
            txtDueDate.setText("");
            JOptionPane.showMessageDialog(null, "Text Fields Cleared!");
        } else if (e.getSource() == btnDelete) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this from table?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Transaction Deleted Successfully!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                JOptionPane.showMessageDialog(null, "Operation Canceled.");
            }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to remove.");
            }
        } else if (e.getSource() == btnEdit) {
            String carID = (String) cmbCarID.getSelectedItem();
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.setValueAt(
                        carID, selectedRow, 0 );
                model.setValueAt(
                        txtCustomerID.getText(), selectedRow, 1 );
                model.setValueAt(
                        txtCustomer.getText(), selectedRow, 2 );
                model.setValueAt (
                        txtRentFee.getText(), selectedRow, 3 );
                model.setValueAt(
                        txtRentHour.getText(), selectedRow, 4 );
                model.setValueAt(txtDate.getText(), selectedRow, 5 );
                model.setValueAt(txtDueDate.getText(), selectedRow, 6 );
                JOptionPane.showMessageDialog(null, "Transaction Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.");
            }
        } else if (e.getSource() == btnAdd) {
            String carID = cmbCarID.getSelectedItem().toString();
            String customerID = txtCustomerID.getText();
            String customerName = txtCustomer.getText();
            String rentFee = txtRentFee.getText();
            String rentHour = txtRentHour.getText();
            String startDateStr = txtDate.getText();
            String dueDateStr = txtDueDate.getText();
            
            try{
                LocalDate startDate = LocalDate.parse(startDateStr, formatter);
                LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);
                
                if(dueDate.isBefore(startDate)){
                    JOptionPane.showMessageDialog(this, "Error, due date cannot be before start date.");
                    return;
                }
                
                JDateChooser cim = new JDateChooser(
                        carID, 
                        customerID, 
                        customerName, 
                        rentFee,
                        rentHour,
                        startDate.format(formatter), 
                        dueDate.format(formatter)
                );
                rentalList.add(cim);
                
                model.addRow(new Object[]{
                    carID,
                    customerID,
                    customerName,
                    rentFee,
                    rentHour,
                    startDate.format(formatter),
                    dueDate.format(formatter)
                });
                
                txtCustomerID.setText("");
                txtCustomer.setText("");
                txtRentFee.setText("");
                txtRentHour.setText("");
                txtDate.setText("");
                txtDueDate.setText("");
            } catch(DateTimeParseException ex){
                JOptionPane.showMessageDialog(this, "Invalid date format! please use MM/dd/yyyy.");
            }
        }
    
}
        private void loadTableData(){
            model.setRowCount(0);
            for(JDateChooser cim : rentalList){
                model.addRow(new Object[]{
                    cim.getCarID(),
                    cim.getCustomerID(),
                    cim.getCustomerName(),
                    cim.getRentFee(),
                    cim.getRentHour(),
                    cim.getStartDate(),
                    cim.getDueDate()
                });
            }
    }
}
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
public class bookingReservation extends JPanel implements ActionListener{
    
    private JLabel lblHeader, lblCustomerID, lblCustomer, lblAddress, lblNumber, lblEmail;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtCustomerID, txtCustomer, txtNumber, txtEmail;
    private JTextArea txaAddress;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private static final String[] tblColumns = {
            "Customer ID",
            "Customer Name",
            "Contact Details",
            "Customer Address",
            "Email Address"
        };
    private JPanel panel;
    private static ArrayList<customerManager> rentalList = new ArrayList<>();
    
    bookingReservation() {
        
        setLayout(null);
        
        lblHeader = new JLabel("Customer");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 16));
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(350, 50, 100, 30);
        add(lblHeader);
        
        lblCustomerID = new JLabel("Customer ID ");
        lblCustomerID.setForeground(Color.BLUE);
        lblCustomerID.setBounds(400, 130, 100, 40);
        add(lblCustomerID);
        
        lblCustomer = new JLabel("Customer Name ");
        lblCustomer.setForeground(Color.BLUE);
        lblCustomer.setBounds(400, 190, 100, 40);
        add(lblCustomer);
        
        lblAddress = new JLabel("Address ");
        lblAddress.setForeground(Color.BLUE);
        lblAddress.setBounds(400, 250, 100, 40);
        add(lblAddress);
        
        lblNumber = new JLabel("Mobile Number ");
        lblNumber.setForeground(Color.BLUE);
        lblNumber.setBounds(400, 310, 100, 40);
        add(lblNumber);
        
        lblEmail = new JLabel("Email Address ");
        lblEmail.setForeground(Color.BLUE);
        lblEmail.setBounds(400, 370, 100, 40);
        add(lblEmail);
        
        txtCustomerID = new JTextField();
        txtCustomerID.setBackground(new Color(240, 240, 244));
        txtCustomerID.setBounds(550, 130, 200, 40);
        add(txtCustomerID);
        
        txtCustomer = new JTextField();
        txtCustomer.setBackground(new Color(240, 240, 244));
        txtCustomer.setBounds(550, 190, 200, 40);
        add(txtCustomer);
        
        txaAddress = new JTextArea();
        txaAddress.setBackground(new Color(240, 240, 244));
        txaAddress.setBounds(550, 250, 200, 40);
        add(txaAddress);
        
        txtNumber = new JTextField();
        txtNumber.setBackground(new Color(240, 240, 244));
        txtNumber.setBounds(550, 310, 200, 40);
        add(txtNumber);
        
        txtEmail = new JTextField();
        txtEmail.setBackground(new Color(240, 240, 244));
        txtEmail.setBounds(550, 370, 200, 40);
        add(txtEmail);
        
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
        lblAddress.setVisible(false);
        lblCustomer.setVisible(false);
        lblHeader.setVisible(false);
        lblCustomerID.setVisible(false);
        lblEmail.setVisible(false);
        lblNumber.setVisible(false);
        txtCustomer.setVisible(false);
        txtCustomerID.setVisible(false);
        txtEmail.setVisible(false);
        txtNumber.setVisible(false);
        txaAddress.setVisible(false);
    }
    
    public void showBookingReservation() {
        scrollPane.setVisible(true);
        btnCancel.setVisible(true);
        btnDelete.setVisible(true);
        btnEdit.setVisible(true);
        btnAdd.setVisible(true);
        lblAddress.setVisible(true);
        lblCustomer.setVisible(true);
        lblHeader.setVisible(true);
        lblCustomerID.setVisible(true);
        lblEmail.setVisible(true);
        lblNumber.setVisible(true);
        txtCustomer.setVisible(true);
        txtCustomerID.setVisible(true);
        txtEmail.setVisible(true);
        txtNumber.setVisible(true);
        txaAddress.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            txtCustomerID.setText("");
            txtCustomer.setText("");
            txaAddress.setText("");
            txtNumber.setText("");
            txtEmail.setText("");
            JOptionPane.showMessageDialog(null, "Text Fields Cleared!");
        } else if (e.getSource() == btnDelete) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this from table?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Cutomer Deleted Successfully!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                JOptionPane.showMessageDialog(null, "Operation Canceled.");
            }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to remove.", "Delete", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnEdit) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.setValueAt(
                        txtCustomerID.getText(), selectedRow, 0 );
                model.setValueAt(
                        txtCustomer.getText(), selectedRow, 1 );
                model.setValueAt(
                        txtNumber.getText(), selectedRow, 2 );
                model.setValueAt (
                        txaAddress.getText(), selectedRow, 3 );
                model.setValueAt(
                        txtEmail.getText(), selectedRow, 4 );
                JOptionPane.showMessageDialog(null, "Customer Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.", "Update", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnAdd) {
            String customerId = txtCustomerID.getText();
            String customerName = txtCustomer.getText();
            String contactNumber = txtNumber.getText();
            String address = txaAddress.getText();
            String emailAddress = txtEmail.getText();
            
            if (!(customerId.isEmpty() || customerName.isEmpty() || contactNumber.isEmpty() || address.isEmpty())) {
                
                customerManager record = new customerManager(
                        customerId,
                        customerName,
                        contactNumber,
                        address,
                        emailAddress
                        );
                rentalList.add(record);
                
            model.addRow(new Object[]{
                customerId,
                customerName,
                contactNumber,
                address,
                emailAddress
            });
            JOptionPane.showMessageDialog(null, "Customer Added Successfully!");
           txtCustomerID.setText("");
           txtCustomer.setText("");
           txaAddress.setText("");
           txtNumber.setText("");
           txtEmail.setText("");
        } else {
                JOptionPane.showMessageDialog(null, "All fields must be Fullfilled.", "Add", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void loadTableData() {
        model.setRowCount(0);
        
        for (customerManager record : rentalList) {
        model.addRow(new Object[]{
            record.getCustomerID(),
            record.getCustomerName(),
            record.getCustomerNumber(),
            record.getAddress(),
            record.getEmailAddress()
        });
    }
}
}
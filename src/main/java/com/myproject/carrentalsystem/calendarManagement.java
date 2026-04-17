/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author hicru
 */
public class calendarManagement extends JFrame implements ActionListener{
    
    private JLabel lblHeader, lblCarID, lblCustomerID, lblCustomer, lblRentFee, lblDate, lblDueDate;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtCustomerID, txtCustomer, txtRentFee, txtDate, txtDueDate;
    private JComboBox<String> cmbCarID;
    protected static final String[] confirmation = {"1", "2"};
    private JTable tblManagement;

    calendarManagement() {
        setSize(1000, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblHeader = new JLabel("Calendar");
        lblHeader.setBounds(50, 50, 100, 30);
        add(lblHeader);
        
        lblCarID = new JLabel("Car ID ");
        lblCarID.setBounds(100, 130, 100, 40);
        add(lblCarID);
        
        lblCustomerID = new JLabel("Customer ID ");
        lblCustomerID.setBounds(100, 190, 100, 40);
        add(lblCustomerID);
        
        lblCustomer = new JLabel("Customer Name ");
        lblCustomer.setBounds(100, 250, 100, 40);
        add(lblCustomer);
        
        lblRentFee = new JLabel("Rental Fee ");
        lblRentFee.setBounds(100, 310, 100, 40);
        add(lblRentFee);
        
        lblDate = new JLabel("Date ");
        lblDate.setBounds(100, 370, 100, 40);
        add(lblDate);
        
        lblDueDate = new JLabel("Due Date ");
        lblDueDate.setBounds(100, 430, 100, 40);
        add(lblDueDate);
        
        cmbCarID = new JComboBox<>(confirmation);
        cmbCarID.setBounds(250, 130, 200, 40);
        add(cmbCarID);
        
        txtCustomerID = new JTextField();
        txtCustomerID.setBounds(250, 190, 200, 40);
        add(txtCustomerID);
        
        txtCustomer = new JTextField();
        txtCustomer.setBounds(250, 250, 200, 40);
        add(txtCustomer);
        
        txtRentFee = new JTextField();
        txtRentFee.setBounds(250, 310, 200, 40);
        add(txtRentFee);
        
        txtDate = new JTextField();
        txtDate.setBounds(250, 370, 200, 40);
        add(txtDate);
        
        txtDueDate = new JTextField();
        txtDueDate.setBounds(250, 430, 200, 40);
        add(txtDueDate);
        
        btnAdd = new JButton("Add");        
        btnAdd.setBounds(150, 500, 100, 40);
        add(btnAdd);
        
        btnEdit = new JButton("Edit");        
        btnEdit.setBounds(350, 500, 100, 40);
        add(btnEdit);
        
        btnDelete = new JButton("Delete");        
        btnDelete.setBounds(150, 570, 100, 40);
        add(btnDelete);
        
        btnCancel = new JButton("Cancel");        
        btnCancel.setBounds(350, 570, 100, 40);
        add(btnCancel);
        
        tblManagement = new JTable();        
        tblManagement.setBounds(500, 100, 400, 400);
        add(tblManagement);
        
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
            txtCustomerID.setText("");
            txtCustomer.setText("");
            txtRentFee.setText("");
            txtDate.setText("");
            txtDueDate.setText("");
        }
    }
    
}

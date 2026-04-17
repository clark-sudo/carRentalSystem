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
public class customerManagement extends JFrame implements ActionListener{
    
    private JLabel lblHeader, lblCustomerID, lblCustomer, lblAddress, lblNumber;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtCustomerID, txtCustomer, txtNumber;
    private JTextArea txtAddress;
    private JTable tblManagement;

    customerManagement() {
        setSize(1000, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblHeader = new JLabel("Customer");
        lblHeader.setBounds(50, 50, 100, 30);
        add(lblHeader);
        
        lblCustomerID = new JLabel("Customer ID ");
        lblCustomerID.setBounds(100, 130, 100, 40);
        add(lblCustomerID);
        
        lblCustomer = new JLabel("Customer Name ");
        lblCustomer.setBounds(100, 190, 100, 40);
        add(lblCustomer);
        
        lblAddress = new JLabel("Address ");
        lblAddress.setBounds(100, 250, 100, 40);
        add(lblAddress);
        
        lblNumber = new JLabel("Mobile Number ");
        lblNumber.setBounds(100, 310, 100, 40);
        add(lblNumber);
        
        txtCustomerID = new JTextField();
        txtCustomerID.setBounds(250, 130, 200, 40);
        add(txtCustomerID);
        
        txtCustomer = new JTextField();
        txtCustomer.setBounds(250, 190, 200, 40);
        add(txtCustomer);
        
        txtAddress = new JTextArea();
        txtAddress.setBounds(250, 250, 200, 40);
        add(txtAddress);
        
        txtNumber = new JTextField();
        txtNumber.setBounds(250, 310, 200, 40);
        add(txtNumber);
        
        btnAdd = new JButton("Add");        
        btnAdd.setBounds(150, 380, 100, 40);
        add(btnAdd);
        
        btnEdit = new JButton("Edit");        
        btnEdit.setBounds(350, 380, 100, 40);
        add(btnEdit);
        
        btnDelete = new JButton("Delete");        
        btnDelete.setBounds(150, 450, 100, 40);
        add(btnDelete);
        
        btnCancel = new JButton("Cancel");        
        btnCancel.setBounds(350, 450, 100, 40);
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
            txtAddress.setText("");
            txtNumber.setText("");
        }
    }
    
}

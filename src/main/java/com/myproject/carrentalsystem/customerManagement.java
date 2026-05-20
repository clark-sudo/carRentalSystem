/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hicru
 */
public class customerManagement extends JFrame implements ActionListener{
    
    private JLabel lblHeader, lblCustomerID, lblCustomer, lblAddress, lblNumber, lblEmail;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtCustomerID, txtCustomer, txtNumber, txtEmail;
    private JTextArea txaAddress;
    private JTable tblManagement;
    private JScrollPane spTable;
    private DefaultTableModel dfltModel;
    private int id;

    customerManagement() {
        getContentPane().setBackground(new Color(45, 52, 54));
        setSize(1200, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblHeader = new JLabel("Customer");
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(50, 50, 100, 30);
        add(lblHeader);
        
        lblCustomerID = new JLabel("Customer ID ");
        lblCustomerID.setForeground(Color.BLUE);
        lblCustomerID.setBounds(100, 130, 100, 40);
        add(lblCustomerID);
        
        lblCustomer = new JLabel("Customer Name ");
        lblCustomer.setForeground(Color.BLUE);
        lblCustomer.setBounds(100, 190, 100, 40);
        add(lblCustomer);
        
        lblAddress = new JLabel("Address ");
        lblAddress.setForeground(Color.BLUE);
        lblAddress.setBounds(100, 250, 100, 40);
        add(lblAddress);
        
        lblNumber = new JLabel("Mobile Number ");
        lblNumber.setForeground(Color.BLUE);
        lblNumber.setBounds(100, 310, 100, 40);
        add(lblNumber);
        
        lblEmail = new JLabel("Email Address ");
        lblEmail.setForeground(Color.BLUE);
        lblEmail.setBounds(100, 370, 100, 40);
        add(lblEmail);
        
        txtCustomerID = new JTextField();
        txtCustomerID.setBounds(250, 130, 200, 40);
        add(txtCustomerID);
        
        txtCustomer = new JTextField();
        txtCustomer.setBounds(250, 190, 200, 40);
        add(txtCustomer);
        
        txaAddress = new JTextArea();
        txaAddress.setBounds(250, 250, 200, 40);
        add(txaAddress);
        
        txtNumber = new JTextField();
        txtNumber.setBounds(250, 310, 200, 40);
        add(txtNumber);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(250, 370, 200, 40);
        add(txtEmail);
        
        btnAdd = new JButton("Add");     
        btnAdd.setBackground(new Color(0, 130, 120));
        btnAdd.setForeground(Color.white);      
        btnAdd.setBounds(150, 440, 100, 40);
        add(btnAdd);
        
        btnEdit = new JButton("Edit");    
        btnEdit.setBackground(new Color(0, 130, 120));
        btnEdit.setForeground(Color.white);      
        btnEdit.setBounds(350, 440, 100, 40);
        add(btnEdit);
        
        btnDelete = new JButton("Delete"); 
        btnDelete.setBackground(new Color(0, 130, 120));
        btnDelete.setForeground(Color.white);         
        btnDelete.setBounds(150, 510, 100, 40);
        add(btnDelete);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(new Color(0, 130, 120));
        btnCancel.setForeground(Color.white);          
        btnCancel.setBounds(350, 510, 100, 40);
        add(btnCancel);
        
        dfltModel = new DefaultTableModel();
        dfltModel.setColumnIdentifiers(new String[]{
        "Customer ID", "Customer Name", "Contact Details", "Customer Address", "Email Address"
        });
        
        tblManagement = new JTable(dfltModel);
        spTable = new JScrollPane(tblManagement);
        spTable.setBounds(500, 100, 600, 500);
        add(spTable);
//        
//        tblManagement = new JTable(dfltModel);        
//        tblManagement.setBounds(500, 100, 400, 400);
//        add(tblManagement);
//        
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
            int selectedRow = tblManagement.getSelectedRow();
            if (selectedRow != -1) {
                dfltModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Customer Removed Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to remove.");
            }
            txtCustomerID.setText("");
            txtCustomer.setText("");
            txaAddress.setText("");
            txtNumber.setText("");
            txtEmail.setText("");
        } else if (e.getSource() == btnAdd) {
//            String strID = txtCustomerID.getText();
//            id = newID(Integer.parseInt(strID));
//            if (id == existingID(id)) {
//                JOptionPane.showMessageDialog(this, "Are you sure this ID is yours?");
//            } else {
          dfltModel.addRow(new Object[]{
          txtCustomerID.getText(),txtCustomer.getText(),txaAddress.getText(),txtNumber.getText(),txtEmail.getText()
          });
//            }
           txtCustomerID.setText("");
           txtCustomer.setText("");
           txaAddress.setText("");
           txtNumber.setText("");
           txtEmail.setText("");
//            String strName = txtCustomer.getText();
//            String strAddress = txaAddress.getText();
//            String strNumber = txtNumber.getText();
//            dfltModel.addRow(new Object[]{
//                    strID,
//                    strName,
//                    strNumber,
//                    strAddress
//                });
        } else if (e.getSource() == btnEdit) {
            int selectedRow = tblManagement.getSelectedRow();
            if (selectedRow != -1) {
                
                dfltModel.setValueAt(txtCustomerID.getText(), selectedRow, 0);
                dfltModel.setValueAt(txtCustomer.getText(), selectedRow, 1);
                dfltModel.setValueAt(txtNumber.getText(), selectedRow, 2);
                dfltModel.setValueAt(txaAddress.getText(), selectedRow, 3);
                dfltModel.setValueAt(txtEmail.getText(), selectedRow, 4);
                JOptionPane.showMessageDialog(null, "Customer Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row first.");
            
//            if (id == existingID()) {
//            tblManagement.setText(" Customer ID: " + id +
//                    "\n Name: " + txtCustomer.getText() +
//                    "\n Address: " + txaAddress.getText() +
//                    "\n Moblie Number: " + txtNumber.getText());
//            }
        }
    }
        }
        
    private int newID (int id) {
        return id;
    }
    private int existingID (int id) {
        int ID = newID(id);
        return ID;
    }
    }
    
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
public class calendarManagement extends JFrame implements ActionListener{
    
    private JLabel lblHeader, lblCarID, lblCustomerID, lblCustomer, lblRentFee, lblRentHour, lblDate, lblDueDate;
    private JButton btnAdd, btnUpdate, btnDelete, btnCancel;
    private JTextField txtCustomerID, txtCustomer, txtRentFee, txtRentHour, txtDate, txtDueDate;
    private JComboBox<String> cmbCarID;
    protected static final String[] confirmation = {"1", "2"};
    private JTable tblManagement;
    private JScrollPane spTable;
    private DefaultTableModel model;
    private static final String[] columnData = {"Car ID", "Customer ID", "Customer Name", "Rental Fee", "Rental Hour", "Date", "Due Date"};
    private static final Object[][] rowData = {};

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
        
        model = new DefaultTableModel(rowData, columnData)/*;
        dfltModel.setColumnIdentifiers(new String[] {
                "Car ID",
                "Customer ID",
                "Customer Name",
                "Rental Fee",
                "Rental Hour",
                "Date",
                "Due Date"
        });*/
            {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblManagement = new JTable(model);
        spTable = new JScrollPane(tblManagement);
        spTable.setBounds(500, 100, 600, 500);
        add(spTable);
        
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
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
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to remove this from table?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Recored Deleted Successfully!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Operation Canceled.");
            }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to remove.");
            }
            txtCustomerID.setText("");
            txtCustomer.setText("");
            txtRentFee.setText("");
            txtRentHour.setText("");
            txtDate.setText("");
            txtDueDate.setText("");
        } else if (e.getSource() == btnUpdate) {
            String carID = (String) cmbCarID.getSelectedItem();
            int selectedRow = tblManagement.getSelectedRow();
            if (selectedRow != -1) {
                model.setValueAt(carID, selectedRow, 0 );
                model.setValueAt(txtCustomerID.getText(), selectedRow, 1);
                model.setValueAt(txtCustomer.getText(), selectedRow, 2);
                model.setValueAt(txtRentFee.getText(), selectedRow, 3);
                model.setValueAt(txtRentHour.getText(), selectedRow, 4);
                model.setValueAt(txtDate.getText(), selectedRow, 5);
                model.setValueAt(txtDueDate.getText(), selectedRow, 6);
                JOptionPane.showMessageDialog(null, "Record Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.");
            }
        } else if (e.getSource() == btnAdd) {
            String carID = (String) cmbCarID.getSelectedItem();
            model.addRow(new Object[]{
                carID,
                txtCustomerID.getText(),
                txtCustomer.getText(),
                txtRentFee.getText(),
                txtRentHour.getText(),
                txtDate.getText(),
                txtDueDate.getText()    }   );
            JOptionPane.showMessageDialog(null, "Record Added Successfully!");
        }
    }
    
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 *

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

**
 *
 * @author hicru
 */

//{"Ctm ID", "Ctr ID", "Cst Name", "C. Fee", "Cstmr Date", "Cstr Date"};
/*

        dfltModel = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
        "Car ID", "Customer ID", "Customer Name", "Rental Fee", "Date", "Due Date"
        });

        tblManagement = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tblManagement);
        scrollPane.setBounds(500, 100, 550, 600);
        add(scrollPane);
        *
        else if(e.getSource() == btnAdd){
            String carID = cmbCarID.getSelectedItem().toString();
            String customerID = txtCustomerID.getText();
            String customerName = txtCustomer.getText();
            String rentFee = txtRentFee.getText();
            String startDateStr = txtDate.getText();
            String dueDateStr = txtDueDate.getText();
            
            try{
                LocalDate startDate = LocalDate.parse(startDateStr, formatter);
                LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);
                
                if(dueDate.isBefore(startDate)){
                    JOptionPane.showMessageDialog(this, "Error, due date cannot be before start date.");
                    return;
                }
                
                model.addRow(new Object[]{
                    carID,
                    customerID,
                    customerName,
                    rentFee,
                    startDate.format(formatter),
                    dueDate.format(formatter)
                });
                
                txtCustomerID.setText("");
                txtCustomer.setText("");
                txtRentFee.setText("");
                txtDate.setText("");
                txtDueDate.setText("");
            } catch(DateTimeParseException ex){
                JOptionPane.showMessageDialog(this, "Invalid date format! please use MM/dd/yyyy.");
            }
        }
    }
    
}
*/
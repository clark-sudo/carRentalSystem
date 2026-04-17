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
public class carManagement extends JFrame implements ActionListener{
    
    private JLabel lblHeader, lblCarID, lblBrand, lblModel, lblAvailable;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtCarID, txtBrand, txtModel;
    private JComboBox<String> cmbAvailable;
    protected static final String[] confirmation = {"Yes", "No"};
    private JTable tblManagement;

    carManagement() {
        setName("Car Management");
        setSize(1000, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblHeader = new JLabel("Car Registration");
        lblHeader.setBounds(50, 50, 100, 30);
        add(lblHeader);
        
        lblCarID = new JLabel("Car ID ");
        lblCarID.setBounds(100, 130, 100, 40);
        add(lblCarID);
        
        lblBrand = new JLabel("Brand ");
        lblBrand.setBounds(100, 190, 100, 40);
        add(lblBrand);
        
        lblModel = new JLabel("Model ");
        lblModel.setBounds(100, 250, 100, 40);
        add(lblModel);
        
        lblAvailable = new JLabel("Availability ");
        lblAvailable.setBounds(100, 310, 100, 40);
        add(lblAvailable);
        
        txtCarID = new JTextField();
        txtCarID.setBounds(250, 130, 200, 40);
        add(txtCarID);
        
        txtBrand = new JTextField("Ex. Toyota");
        txtBrand.setBounds(250, 190, 200, 40);
        add(txtBrand);
        
        txtModel = new JTextField("Ex. Innova");
        txtModel.setBounds(250, 250, 200, 40);
        add(txtModel);
        
        cmbAvailable = new JComboBox<>(confirmation);
        cmbAvailable.setBounds(250, 310, 200, 40);
        add(cmbAvailable);
        
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
            txtCarID.setText("");
            txtBrand.setText("");
            txtModel.setText("");
        }
    }
    
}

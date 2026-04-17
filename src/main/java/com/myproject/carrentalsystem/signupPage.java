/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author hicru
 */
public class signupPage extends JFrame implements ActionListener{
    
    private JLabel lblHeader, lblUsername, lblPassword;
    private JButton btnCreate, btnReset, btnSignin;
    private JTextField txtUsername, txtPassword;
    
    private String username = "admin";
    private String password = "admin123";
    protected static final ArrayList<String> screenSizes = new ArrayList<>(){{
        add("Small screen");
        add("Medium screen");
        add("Normal screen");
        }};

    signupPage() {
        this("Normal screen"); //Default
    }
    signupPage(String screenType) {
        setName("Car Rental");
        
        if (screenType.equals("Small screen")) {
        setSize(400, 600);
        } else if (screenType.equals("Medium screen")) {
        setSize(600, 700);
        } else {
        setSize(700, 600);
        }
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblHeader = new JLabel("Car Rental App");
        lblHeader.setBounds(50, 50, 100, 100);
        add(lblHeader);
        
        lblUsername = new JLabel("Enter new Username: ", SwingConstants.RIGHT);
        lblUsername.setBounds(100, 200, 200, 40);
        add(lblUsername);
        
        lblPassword = new JLabel("Enter new Password: ", SwingConstants.RIGHT);
        lblPassword.setBounds(100, 260, 200, 40);
        add(lblPassword);
        
        txtUsername = new JTextField("Username");
        txtUsername.setBounds(350, 200, 200, 40);
        add(txtUsername);
        
        txtPassword = new JTextField("********");
        txtPassword.setBounds(350, 260, 200, 40);
        add(txtPassword);
        
        btnCreate = new JButton("SignIn");        
        btnCreate.setBounds(230, 330, 80, 40);
        add(btnCreate);
        
        btnReset = new JButton("Reset");        
        btnReset.setBounds(400, 330, 80, 40);
        add(btnReset);
        
        btnSignin = new JButton("Already have an account?");        
        btnSignin.setBounds(200, 400, 310, 40);
        add(btnSignin);
        
        btnCreate.addActionListener(this);
        btnReset.addActionListener(this);
        btnSignin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCreate) {
            dispose();
            loginPage lp = new loginPage();
            lp.setVisible(true);
        } else if (e.getSource() == btnReset) {
            txtUsername.setText("");
            txtPassword.setText("");
        } else if (e.getSource() == btnSignin) {
            dispose();
            loginPage lp = new loginPage();
            lp.setVisible(true);
        }
    }
}

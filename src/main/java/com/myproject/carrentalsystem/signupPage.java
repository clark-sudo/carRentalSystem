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

/**
 *
 * @author hicru
 */
public class signupPage extends JFrame implements ActionListener{
    
    private JLabel lblHeader, lblUsername, lblPassword;
    private JButton btnCreate, btnReset, btnSignin;
    private JTextField txtUsername;
    private JPasswordField pssPassword;
    
    private String username = "admin";
    private String password = "admin123";
    protected static final ArrayList<String> screenSizes = new ArrayList<>(){{
        add("Small screen");
        add("Medium screen");
        add("Normal screen");
    }};
    protected static final ArrayList<String> darkMode = new ArrayList<>(){{
        add("ON");
        add("OFF");
        }};

    signupPage() {
        this("Normal screen"); //Default
    }
    signupPage(String screenType) {
        
        if (screenType.equals("ON")){
        getContentPane().setBackground(new Color(45, 52, 54));
        } else if (screenType.equals("OFF")){
        getContentPane().setBackground(new Color(245, 245, 220));
        } else {
        }
        
        setTitle("Car Rental");
        setSize(700, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        lblHeader = new JLabel("Register Account");
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setBounds(50, 50, 200, 30);
        add(lblHeader);
        
        lblUsername = new JLabel("Enter new Username: ", SwingConstants.RIGHT);
        lblUsername.setForeground(Color.BLUE);
        lblUsername.setBounds(100, 200, 200, 40);
        add(lblUsername);
        
        lblPassword = new JLabel("Enter new Password: ", SwingConstants.RIGHT);
        lblPassword.setForeground(Color.BLUE);
        lblPassword.setBounds(100, 260, 200, 40);
        add(lblPassword);
        
        txtUsername = new JTextField("Username");
        txtUsername.setBounds(350, 200, 200, 40);
        add(txtUsername);
        
        pssPassword = new JPasswordField("********");
        pssPassword.setBounds(350, 260, 200, 40);
        add(pssPassword);
        
        btnCreate = new JButton("SignIn");
        btnCreate.setBackground(new Color(66, 133, 244));
        btnCreate.setForeground(Color.white);
        btnCreate.setBounds(230, 330, 80, 40);
        add(btnCreate);
        
        btnReset = new JButton("Reset");  
        btnReset.setBackground(new Color(66, 133, 244));
        btnReset.setForeground(Color.white); 
        btnReset.setBounds(400, 330, 80, 40);
        add(btnReset);
        
        btnSignin = new JButton("Already have an account?"); 
        btnSignin.setBackground(new Color(66, 133, 244));
        btnSignin.setForeground(Color.white);
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
            pssPassword.setText("");
        } else if (e.getSource() == btnSignin) {
            dispose();
            loginPage lp = new loginPage();
            lp.setVisible(true);
        }
    }
}
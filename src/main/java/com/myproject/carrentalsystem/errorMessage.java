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
public class errorMessage extends JFrame implements ActionListener{
    
    private JLabel lblMessage;
    private JButton btnLogin;
    
    errorMessage(){
        setSize(350, 150);
        setLayout(null);
        setLocationRelativeTo(this);
        
        lblMessage = new JLabel ("Please enter valid username and password.");
        lblMessage.setBounds(50, 10, 250, 40);
        add(lblMessage);
        
        btnLogin = new JButton ("OK");
        btnLogin.setBounds(250, 60, 60, 30);
        add(btnLogin);
        
        btnLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            dispose();
        }
    }
}

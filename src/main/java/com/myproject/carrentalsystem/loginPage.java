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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

/**
 *
 * @author hicru
 */
public class loginPage extends JFrame implements ActionListener {

    private JLabel lblHeader, lblUsername, lblPassword;
    private JButton btnLogin, btnReset, btnSignup;
    private JTextField txtUsername;
    private JPasswordField pssPassword;

    loginPage() {
        setTitle("Car Rental");
        getContentPane().setBackground(new Color(20, 80, 180));
        setSize(650, 450);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        lblHeader = new JLabel("Car Rental App");
        lblHeader.setForeground(Color.white);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setBounds(50, 50, 200, 30);
        add(lblHeader);

        lblUsername = new JLabel("Enter Username: ", SwingConstants.RIGHT);
        lblUsername.setForeground(Color.white);
        lblUsername.setBounds(100, 130, 200, 40);
        add(lblUsername);

        lblPassword = new JLabel("Enter Password: ", SwingConstants.RIGHT);
        lblPassword.setForeground(Color.white);
        lblPassword.setBounds(100, 190, 200, 40);
        add(lblPassword);

        txtUsername = new JTextField("Username");
        txtUsername.setBounds(350, 130, 200, 40);
        add(txtUsername);

        pssPassword = new JPasswordField("********");
        pssPassword.setBounds(350, 190, 200, 40);
        add(pssPassword);

        btnLogin = new JButton("LogIn");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setBounds(240, 260, 80, 40);
        add(btnLogin);

        btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Arial", Font.BOLD, 16));
        btnReset.setBounds(410, 260, 80, 40);
        add(btnReset);

        btnSignup = new JButton("Create new account");
        btnSignup.setBounds(200, 330, 350, 40);
        add(btnSignup);

        btnLogin.addActionListener(this);
        btnReset.addActionListener(this);
        btnSignup.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {

            String username = txtUsername.getText();
            String password = String.valueOf(pssPassword.getPassword());

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

            try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, username);
                pst.setString(2, password);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {

                    JOptionPane.showMessageDialog(this, "Login Successful!");

                    dispose();
                    homePage hp = new homePage();
                    hp.setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Invalid Username or Password.",
                            "Wrong Credentials",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Database Error: " + ex.getMessage());
            }
        } else if (e.getSource() == btnReset) {
            txtUsername.setText("");
            pssPassword.setText("");
        } else if (e.getSource() == btnSignup) {
            dispose();
            signupPage sp = new signupPage();
            sp.setVisible(true);
        }
    }
}

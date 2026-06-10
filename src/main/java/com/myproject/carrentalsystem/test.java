/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author james
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
    Connection con = DBConnection.getConnection();

    if (con != null) {
        JOptionPane.showMessageDialog(
                null,
                "Connected to Database!"
        );
    }
} catch (Exception e) {
    e.printStackTrace();
}
    }
    
}

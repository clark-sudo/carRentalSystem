/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.myproject.carrentalsystem;

//import javax.swing.*;

/**
 *
 * @author hicru
 */
public class CarRentalSystem {
    
    public static void main(String[] args) {
//        String choice = (String) JOptionPane.showInputDialog(null, "Plese confirm if you wish to switch to Dark Mode.",
//                "Dark Mode", JOptionPane.QUESTION_MESSAGE, null, signupPage.darkMode.toArray(), signupPage.darkMode.get(1));
//        
//        if (choice == null) choice = "Normal Screen";
        
        loginPage lp = new loginPage();
        lp.setVisible(true);
    }
}

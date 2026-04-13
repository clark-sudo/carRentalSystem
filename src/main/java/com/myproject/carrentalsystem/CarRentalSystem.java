/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.myproject.carrentalsystem;

import javax.swing.*;

/**
 *
 * @author hicru
 */
public class CarRentalSystem {
    
    public static void main(String[] args) {
        String choice = (String) JOptionPane.showInputDialog(null, "Select Screen Size", "Screen selection", 
                JOptionPane.QUESTION_MESSAGE, null, homePage.screenSizes.toArray(), homePage.screenSizes.get(2));
        
        if (choice == null) choice = "Normal Screen";
        
        loginPage lp = new loginPage(choice);
        lp.setVisible(true);
    }
}

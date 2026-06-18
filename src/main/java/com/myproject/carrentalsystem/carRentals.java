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
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hicru
 */
public class carRentals extends JPanel implements ActionListener {

    private JLabel lblHeader, lblColor, lblMake, lblModel, lblPrice;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtColor, txtMake, txtModel, txtPrice;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private static final String[] tblColumns = {
        "Car ID",
        "Car Color",
        "Car Make",
        "Car Model",
        "Rental Price",
        "Status"
    };
    private JPanel panel;
    private static ArrayList<carManager> carList = new ArrayList<>();
    private boolean isEditing = false;

    carRentals() {

        setSize(1370, 730);
        setLayout(null);
//        setOpaque(false);

        lblHeader = new JLabel("Car Registration");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 16));
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(350, 50, 200, 30);
        add(lblHeader);

        lblColor = new JLabel("Car Color ");
        lblColor.setForeground(Color.BLUE);
        lblColor.setBounds(400, 130, 100, 40);
        add(lblColor);

        lblMake = new JLabel("Make ");
        lblMake.setForeground(Color.BLUE);
        lblMake.setBounds(400, 190, 100, 40);
        add(lblMake);

        lblModel = new JLabel("Model ");
        lblModel.setForeground(Color.BLUE);
        lblModel.setBounds(400, 250, 100, 40);
        add(lblModel);

        lblPrice = new JLabel("Rental Price ");
        lblPrice.setForeground(Color.BLUE);
        lblPrice.setBounds(400, 310, 100, 40);
        add(lblPrice);

        txtColor = new JTextField();
        txtColor.setBackground(new Color(240, 240, 244));
        txtColor.setBounds(550, 130, 200, 40);
        add(txtColor);

        txtMake = new JTextField("Ex. Toyota");
        txtMake.setBackground(new Color(240, 240, 244));
        txtMake.setBounds(550, 190, 200, 40);
        add(txtMake);

        txtModel = new JTextField("Ex. Innova");
        txtModel.setBackground(new Color(240, 240, 244));
        txtModel.setBounds(550, 250, 200, 40);
        add(txtModel);

        txtPrice = new JTextField();
        txtPrice.setBackground(new Color(240, 240, 244));
        txtPrice.setBounds(550, 310, 200, 40);
        add(txtPrice);

        btnAdd = new JButton("Add");
        btnAdd.setBackground(new Color(0, 130, 120));
        btnAdd.setForeground(Color.white);
        btnAdd.setBounds(450, 440, 100, 40);
        add(btnAdd);

        btnEdit = new JButton("Edit");
        btnEdit.setBackground(new Color(0, 130, 120));
        btnEdit.setForeground(Color.white);
        btnEdit.setBounds(650, 440, 100, 40);
        add(btnEdit);

        btnDelete = new JButton("Delete");
        btnDelete.setBackground(new Color(0, 130, 120));
        btnDelete.setForeground(Color.white);
        btnDelete.setBounds(450, 510, 100, 40);
        add(btnDelete);

        btnCancel = new JButton("Clear");
        btnCancel.setBackground(new Color(0, 130, 120));
        btnCancel.setForeground(Color.white);
        btnCancel.setBounds(650, 510, 100, 40);
        add(btnCancel);

        model = new DefaultTableModel(tblColumns, 0);
        table = new JTable(model);
        loadTableData();
        scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(177, 218, 220));
        scrollPane.setBounds(800, 130, 500, 500);
        add(scrollPane);

        panel = new JPanel();
        panel.setBackground(new Color(245, 245, 220));
        panel.setBounds(300, 0, 1070, 700);
        add(panel);

        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnCancel.addActionListener(this);

        scrollPane.setVisible(false);
        btnCancel.setVisible(false);
        btnDelete.setVisible(false);
        btnEdit.setVisible(false);
        btnAdd.setVisible(false);
        lblColor.setVisible(false);
        lblHeader.setVisible(false);
        lblMake.setVisible(false);
        lblModel.setVisible(false);
        lblPrice.setVisible(false);
        txtPrice.setVisible(false);
        txtModel.setVisible(false);
        txtMake.setVisible(false);
        txtColor.setVisible(false);
    }

    public void showCarRentals() {
        scrollPane.setVisible(true);
        btnCancel.setVisible(true);
        btnDelete.setVisible(true);
        btnEdit.setVisible(true);
        btnAdd.setVisible(true);
        lblColor.setVisible(true);
        lblHeader.setVisible(true);
        lblMake.setVisible(true);
        lblModel.setVisible(true);
        lblPrice.setVisible(true);
        txtPrice.setVisible(true);
        txtModel.setVisible(true);
        txtMake.setVisible(true);
        txtColor.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            txtColor.setText("");
            txtMake.setText("");
            txtModel.setText("");
            txtPrice.setText("");
            JOptionPane.showMessageDialog(null, "Text Fields Cleared!");
        } else if (e.getSource() == btnDelete) {

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please select a row."
                );
                return;
            }

            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Delete this car?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                try {

                    String carID
                            = model.getValueAt(selectedRow, 0).toString();

                    Connection con
                            = DBConnection.getConnection();

                    String sql
                            = "DELETE FROM cars WHERE car_id=?";

                    PreparedStatement pst
                            = con.prepareStatement(sql);

                    pst.setString(1, carID);

                    pst.executeUpdate();

                    loadTableData();

                    JOptionPane.showMessageDialog(
                            null,
                            "Car Deleted Successfully!"
                    );

                } catch (Exception ex) {

                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(
                            null,
                            "Error deleting car!"
                    );
                }
            }
        } else if (e.getSource() == btnEdit) {

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please select a row."
                );
                return;
            }

            if (!isEditing) {

                isEditing = true;

                txtColor.setText(
                        model.getValueAt(selectedRow, 1).toString());

                txtMake.setText(
                        model.getValueAt(selectedRow, 2).toString());

                txtModel.setText(
                        model.getValueAt(selectedRow, 3).toString());

                txtPrice.setText(
                        model.getValueAt(selectedRow, 4).toString());

                btnAdd.setEnabled(false);

                JOptionPane.showMessageDialog(
                        null,
                        "Edit the fields then click Edit again."
                );

            } else {

                try {

                    String carID
                            = model.getValueAt(selectedRow, 0).toString();

                    Connection con
                            = DBConnection.getConnection();

                    String sql
                            = "UPDATE cars "
                            + "SET color=?, make=?, model=?, rental_price=? "
                            + "WHERE car_id=?";

                    PreparedStatement pst
                            = con.prepareStatement(sql);

                    pst.setString(1, txtColor.getText());
                    pst.setString(2, txtMake.getText());
                    pst.setString(3, txtModel.getText());
                    pst.setDouble(4,
                            Double.parseDouble(txtPrice.getText()));
                    pst.setString(5, carID);

                    pst.executeUpdate();

                    loadTableData();

                    JOptionPane.showMessageDialog(
                            null,
                            "Car Updated Successfully!"
                    );

                    isEditing = false;

                    btnAdd.setEnabled(true);

                    txtColor.setText("");
                    txtMake.setText("");
                    txtModel.setText("");
                    txtPrice.setText("");

                } catch (Exception ex) {

                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(
                            null,
                            "Error updating car!"
                    );
                }
            }
        } else if (e.getSource() == btnAdd) {

            String carColor = txtColor.getText().trim();
            String brandMake = txtMake.getText().trim();
            String carModel = txtModel.getText().trim();
            String rentalPrice = txtPrice.getText().trim();

            if (carColor.isEmpty() || brandMake.isEmpty()
                    || carModel.isEmpty() || rentalPrice.isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "All fields must be fulfilled.",
                        "Add",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try {

                double price = Double.parseDouble(rentalPrice);

                Connection con = DBConnection.getConnection();

                String sql
                        = "INSERT INTO cars(color, make, model, rental_price, available) "
                        + "VALUES (?, ?, ?, ?, 'Yes')";

                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, carColor);
                pst.setString(2, brandMake);
                pst.setString(3, carModel);
                pst.setDouble(4, price);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        null,
                        "Car Added Successfully!"
                );

                loadTableData();

                txtColor.setText("");
                txtMake.setText("");
                txtModel.setText("");
                txtPrice.setText("");

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Rental price must be numeric."
                );

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        null,
                        "Error saving car!"
                );
            }
        }
    }

    private void loadTableData() {

        model.setRowCount(0);

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM cars";

            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getInt("car_id"),
                    rs.getString("color"),
                    rs.getString("make"),
                    rs.getString("model"),
                    rs.getDouble("rental_price"),
                    rs.getString("available")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

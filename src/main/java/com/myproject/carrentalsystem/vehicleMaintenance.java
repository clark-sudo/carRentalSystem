/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hicru
 */
public class vehicleMaintenance extends JPanel implements ActionListener {

    private JLabel lblHeader, lblVehicle, lblType, lblDescription, lblCost, lblDate, lblDueDate;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtDescription, txtCost, txtDate, txtDueDate;
    private JComboBox<CarItem> cmbVehicle;
    private JComboBox<String> cmbType;
    protected static final String[] tblColumns = {
        "Vehicle",
        "Maintenance Type",
        "Description",
        "Total Cost",
        "Start Date",
        "End Date"
    };
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    protected static final String[] serviceTypes = {
        "Routine Checkup",
        "Interim Car Service Intervals",
        "Full Car Service Intervals",
        "Major Car Service Intervals",
        "Oil Change",
        "Body Repair",
        "Others (State in the description)"
    };
    private JPanel panel;
    private static ArrayList<repairManager> maintenanceHistory = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private boolean isEditing = false;

    vehicleMaintenance() {

        setLayout(null);

        lblHeader = new JLabel("Repair and Maintenance");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 16));
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(350, 50, 200, 30);
        add(lblHeader);

        lblVehicle = new JLabel("Vehicle ");
        lblVehicle.setForeground(Color.BLUE);
        lblVehicle.setBounds(400, 130, 100, 40);
        add(lblVehicle);

        lblType = new JLabel("Type ");
        lblType.setForeground(Color.BLUE);
        lblType.setBounds(400, 190, 100, 40);
        add(lblType);

        lblDescription = new JLabel("Description ");
        lblDescription.setForeground(Color.BLUE);
        lblDescription.setBounds(400, 250, 100, 40);
        add(lblDescription);

        lblCost = new JLabel("Total Cost ");
        lblCost.setForeground(Color.BLUE);
        lblCost.setBounds(400, 310, 100, 40);
        add(lblCost);

        lblDate = new JLabel("Date ");
        lblDate.setForeground(Color.BLUE);
        lblDate.setBounds(400, 370, 100, 40);
        add(lblDate);

        lblDueDate = new JLabel("End Date ");
        lblDueDate.setForeground(Color.BLUE);
        lblDueDate.setBounds(400, 430, 100, 40);
        add(lblDueDate);

        cmbType = new JComboBox<>(serviceTypes);
        cmbType.setBounds(550, 190, 200, 40);
        add(cmbType);

        // Vehicle dropdown — loaded from DB, shows all registered cars
        cmbVehicle = new JComboBox<>();
        cmbVehicle.setBounds(550, 130, 200, 40);
        add(cmbVehicle);

        txtDescription = new JTextField();
        txtDescription.setBackground(new Color(240, 240, 244));
        txtDescription.setBounds(550, 250, 200, 40);
        add(txtDescription);

        txtCost = new JTextField();
        txtCost.setBackground(new Color(240, 240, 244));
        txtCost.setBounds(550, 310, 200, 40);
        add(txtCost);

        txtDate = new JTextField();
        txtDate.setBackground(new Color(240, 240, 244));
        txtDate.setBounds(550, 370, 200, 40);
        add(txtDate);

        txtDueDate = new JTextField();
        txtDueDate.setBackground(new Color(240, 240, 244));
        txtDueDate.setBounds(550, 430, 200, 40);
        add(txtDueDate);

        btnAdd = new JButton("Add");
        btnAdd.setBackground(new Color(0, 130, 120));
        btnAdd.setForeground(Color.white);
        btnAdd.setBounds(450, 560, 100, 40);
        add(btnAdd);

        btnEdit = new JButton("Edit");
        btnEdit.setBackground(new Color(0, 130, 120));
        btnEdit.setForeground(Color.white);
        btnEdit.setBounds(650, 560, 100, 40);
        add(btnEdit);

        btnDelete = new JButton("Delete");
        btnDelete.setBackground(new Color(0, 130, 120));
        btnDelete.setForeground(Color.white);
        btnDelete.setBounds(450, 630, 100, 40);
        add(btnDelete);

        btnCancel = new JButton("Clear");
        btnCancel.setBackground(new Color(0, 130, 120));
        btnCancel.setForeground(Color.white);
        btnCancel.setBounds(650, 630, 100, 40);
        add(btnCancel);

        model = new DefaultTableModel(tblColumns, 0);
        table = new JTable(model);
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

        setAllVisible(false);
    }

    // -----------------------------------------------------------------------
    // Visibility helpers
    // -----------------------------------------------------------------------

    private void setAllVisible(boolean visible) {
        lblCost.setVisible(visible);
        scrollPane.setVisible(visible);
        btnCancel.setVisible(visible);
        btnDelete.setVisible(visible);
        btnEdit.setVisible(visible);
        btnAdd.setVisible(visible);
        lblVehicle.setVisible(visible);
        lblDate.setVisible(visible);
        lblDueDate.setVisible(visible);
        lblHeader.setVisible(visible);
        lblDescription.setVisible(visible);
        lblType.setVisible(visible);
        txtDate.setVisible(visible);
        txtDueDate.setVisible(visible);
        txtDescription.setVisible(visible);
        cmbVehicle.setVisible(visible);
        txtCost.setVisible(visible);
        cmbType.setVisible(visible);
    }

    public void showVehicleMaintenance() {
        loadCars();
        loadTableData();
        setAllVisible(true);
    }

    private void loadCars() {
        cmbVehicle.removeAllItems();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT car_id, color, make, model, rental_price, status FROM cars";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CarItem car = new CarItem(
                        rs.getInt("car_id"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("color"),
                        rs.getDouble("rental_price"),
                        rs.getString("status")
                );
                cmbVehicle.addItem(car);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTableData() {
        model.setRowCount(0);
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT vm.car_id, c.color, c.make, c.model, "
                    + "vm.maintenance_type, vm.description, vm.cost, vm.date, vm.end_date "
                    + "FROM vehicle_maintenance vm "
                    + "LEFT JOIN cars c ON vm.car_id = c.car_id";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String carId = rs.getString("car_id");
                String color = rs.getString("color");
                String make = rs.getString("make");
                String modelName = rs.getString("model");
                // Display as "Color - Make - Model" to match the dropdown label
                String vehicleLabel = (color != null && make != null && modelName != null)
                        ? color + " - " + make + " - " + modelName
                        : carId;
                model.addRow(new Object[]{
                    vehicleLabel,
                    rs.getString("maintenance_type"),
                    rs.getString("description"),
                    rs.getDouble("cost"),
                    rs.getString("date"),
                    rs.getString("end_date")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    private void updateCarStatus(int carId, String status) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE cars SET status = ? WHERE car_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, status);
            pst.setInt(2, carId);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            txtDescription.setText("");
            txtCost.setText("");
            txtDate.setText("");
            txtDueDate.setText("");
            JOptionPane.showMessageDialog(null, "Fields Cleared!");

        } else if (e.getSource() == btnDelete) {
            handleDelete();

        } else if (e.getSource() == btnEdit) {
            handleEdit();

        } else if (e.getSource() == btnAdd) {
            handleAdd();
        }
    }

    private void handleAdd() {
        CarItem selectedCar = (CarItem) cmbVehicle.getSelectedItem();
        if (selectedCar == null) {
            JOptionPane.showMessageDialog(this, "Please select a vehicle.", "Add", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String type = cmbType.getSelectedItem().toString();
        String desc = txtDescription.getText().trim();
        String costStr = txtCost.getText().trim();
        String dateStr = txtDate.getText().trim();
        String endDateStr = txtDueDate.getText().trim();

        if (desc.isEmpty() || costStr.isEmpty() || dateStr.isEmpty() || endDateStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled.", "Add", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double cost;
        LocalDate startDate, endDate;
        try {
            cost = Double.parseDouble(costStr);
            startDate = LocalDate.parse(dateStr, formatter);
            endDate = LocalDate.parse(endDateStr, formatter);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric cost.", "Add", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(null, "Invalid date format! Please use MM/dd/yyyy.", "Add", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (endDate.isBefore(startDate)) {
            JOptionPane.showMessageDialog(this, "End date cannot be before start date.", "Add", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int carId = selectedCar.getCarId();
        String vehicleLabel = selectedCar.toString();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO vehicle_maintenance (car_id, maintenance_type, description, cost, date, end_date) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, carId);
            pst.setString(2, type);
            pst.setString(3, desc);
            pst.setDouble(4, cost);
            pst.setDate(5, java.sql.Date.valueOf(startDate));
            pst.setDate(6, java.sql.Date.valueOf(endDate));
            pst.executeUpdate();

            // Set the car's status to "Maintenance"
            updateCarStatus(carId, "Maintenance");

            model.addRow(new Object[]{
                vehicleLabel,
                type,
                desc,
                cost,
                startDate.format(formatter),
                endDate.format(formatter)
            });

            JOptionPane.showMessageDialog(null, "Maintenance record added and car status set to Maintenance!");

            txtDescription.setText("");
            txtCost.setText("");
            txtDate.setText("");
            txtDueDate.setText("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
        }
    }

    private void handleDelete() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to remove.", "Delete", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(null,
                "Do you want to remove this maintenance record?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Operation Canceled.");
            return;
        }

       
        String vehicleLabel = model.getValueAt(selectedRow, 0).toString();
        String type = model.getValueAt(selectedRow, 1).toString();
        String desc = model.getValueAt(selectedRow, 2).toString();
        String costStr = model.getValueAt(selectedRow, 3).toString();
        String date = model.getValueAt(selectedRow, 4).toString();

        try {
            Connection con = DBConnection.getConnection();

        
            String findSql = "SELECT vm.car_id FROM vehicle_maintenance vm "
                    + "LEFT JOIN cars c ON vm.car_id = c.car_id "
                    + "WHERE vm.maintenance_type = ? AND vm.description = ? "
                    + "AND vm.cost = ? AND vm.date = ? LIMIT 1";
            PreparedStatement findPst = con.prepareStatement(findSql);
            findPst.setString(1, type);
            findPst.setString(2, desc);
            findPst.setDouble(3, Double.parseDouble(costStr));
            findPst.setString(4, date);
            ResultSet rs = findPst.executeQuery();

            int carIdToRestore = -1;
            if (rs.next()) {
                carIdToRestore = rs.getInt("car_id");
            }

            
            String deleteSql = "DELETE FROM vehicle_maintenance "
                    + "WHERE maintenance_type = ? AND description = ? AND cost = ? AND date = ? LIMIT 1";
            PreparedStatement deletePst = con.prepareStatement(deleteSql);
            deletePst.setString(1, type);
            deletePst.setString(2, desc);
            deletePst.setDouble(3, Double.parseDouble(costStr));
            deletePst.setString(4, date);
            deletePst.executeUpdate();

            
            if (carIdToRestore != -1) {
                String checkSql = "SELECT COUNT(*) FROM vehicle_maintenance WHERE car_id = ?";
                PreparedStatement checkPst = con.prepareStatement(checkSql);
                checkPst.setInt(1, carIdToRestore);
                ResultSet checkRs = checkPst.executeQuery();
                if (checkRs.next() && checkRs.getInt(1) == 0) {
                    updateCarStatus(carIdToRestore, "Available");
                }
            }

            model.removeRow(selectedRow);
            JOptionPane.showMessageDialog(null, "Maintenance Record Deleted Successfully!", "Warning", JOptionPane.WARNING_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting record: " + ex.getMessage());
        }
    }

    private void handleEdit() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to edit.", "Update", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isEditing) {
            isEditing = true;

         
            String vehicleLabel = model.getValueAt(selectedRow, 0).toString();
            for (int i = 0; i < cmbVehicle.getItemCount(); i++) {
                if (cmbVehicle.getItemAt(i).toString().equals(vehicleLabel)) {
                    cmbVehicle.setSelectedIndex(i);
                    break;
                }
            }
            cmbType.setSelectedItem(model.getValueAt(selectedRow, 1).toString());
            txtDescription.setText(model.getValueAt(selectedRow, 2).toString());
            txtCost.setText(model.getValueAt(selectedRow, 3).toString());
            txtDate.setText(model.getValueAt(selectedRow, 4).toString());
            txtDueDate.setText(model.getValueAt(selectedRow, 5) != null
                    ? model.getValueAt(selectedRow, 5).toString() : "");

            btnAdd.setEnabled(false);
            JOptionPane.showMessageDialog(null, "You can now edit the fields. Click Edit again to save.");

        } else {
            CarItem selectedCar = (CarItem) cmbVehicle.getSelectedItem();
            if (selectedCar == null) return;

            String newType = cmbType.getSelectedItem().toString();
            String newDesc = txtDescription.getText().trim();
            String newCostStr = txtCost.getText().trim();
            String newDateStr = txtDate.getText().trim();
            String newEndDateStr = txtDueDate.getText().trim();

            double newCost;
            LocalDate newStartDate, newEndDate;
            try {
                newCost = Double.parseDouble(newCostStr);
                newStartDate = LocalDate.parse(newDateStr, formatter);
                newEndDate = LocalDate.parse(newEndDateStr, formatter);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric cost.", "Update", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid date format! Please use MM/dd/yyyy.", "Update", JOptionPane.ERROR_MESSAGE);
                return;
            }

          
            String oldType = model.getValueAt(selectedRow, 1).toString();
            String oldDesc = model.getValueAt(selectedRow, 2).toString();
            String oldCost = model.getValueAt(selectedRow, 3).toString();
            String oldDate = model.getValueAt(selectedRow, 4).toString();

            try {
                Connection con = DBConnection.getConnection();
                String sql = "UPDATE vehicle_maintenance SET car_id = ?, maintenance_type = ?, "
                        + "description = ?, cost = ?, date = ?, end_date = ? "
                        + "WHERE maintenance_type = ? AND description = ? AND cost = ? AND date = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, selectedCar.getCarId());
                pst.setString(2, newType);
                pst.setString(3, newDesc);
                pst.setDouble(4, newCost);
                pst.setDate(5, java.sql.Date.valueOf(newStartDate));
                pst.setDate(6, java.sql.Date.valueOf(newEndDate));
                pst.setString(7, oldType);
                pst.setString(8, oldDesc);
                pst.setDouble(9, Double.parseDouble(oldCost));
                pst.setString(10, oldDate);
                pst.executeUpdate();

                model.setValueAt(selectedCar.toString(), selectedRow, 0);
                model.setValueAt(newType, selectedRow, 1);
                model.setValueAt(newDesc, selectedRow, 2);
                model.setValueAt(newCostStr, selectedRow, 3);
                model.setValueAt(newStartDate.format(formatter), selectedRow, 4);
                model.setValueAt(newEndDate.format(formatter), selectedRow, 5);

                JOptionPane.showMessageDialog(null, "Maintenance Record Updated Successfully!");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Update Error: " + ex.getMessage());
                return;
            }

            isEditing = false;
            btnAdd.setEnabled(true);
            table.clearSelection();
            txtDescription.setText("");
            txtCost.setText("");
            txtDate.setText("");
            txtDueDate.setText("");
        }
    }
}

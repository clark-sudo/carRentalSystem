/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hicru
 */
public class rentalInvoices extends JPanel implements ActionListener {

    private JLabel lblHeader, lblVehicle, lblAvailable, lblCustomer, lblRentFee, lblRentHour, lblDate, lblDueDate;
    private JButton btnAdd, btnEdit, btnDelete, btnCancel;
    private JTextField txtAvailable, txtRentFee, txtRentHour, txtDate, txtDueDate;
    private JComboBox<CarItem> cmbVehicle;
    private JComboBox<String> cmbCustomer;
    private JPanel panel;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private static final String[] tblColumns = {
        "Vehicle",
        "Customer Name",
        "Rental Fee",
        "Rental Hour",
        "Start Date",
        "Due Date"
    };
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static ArrayList<availableManager> rentalList = new ArrayList<>();
    private boolean isEditing = false;

    rentalInvoices() {

        setLayout(null);

        lblHeader = new JLabel("Record");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 16));
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(350, 50, 100, 30);
        add(lblHeader);

        lblVehicle = new JLabel("Vehicle ");
        lblVehicle.setForeground(Color.BLUE);
        lblVehicle.setBounds(400, 130, 100, 40);
        add(lblVehicle);

        lblAvailable = new JLabel("Available ");
        lblAvailable.setForeground(Color.BLUE);
        lblAvailable.setBounds(400, 190, 100, 40);
        add(lblAvailable);

        lblCustomer = new JLabel("Customer Name ");
        lblCustomer.setForeground(Color.BLUE);
        lblCustomer.setBounds(400, 250, 120, 40);
        add(lblCustomer);

        lblRentFee = new JLabel("Rental Fee ");
        lblRentFee.setForeground(Color.BLUE);
        lblRentFee.setBounds(400, 310, 100, 40);
        add(lblRentFee);

        lblRentHour = new JLabel("Rental Hour ");
        lblRentHour.setForeground(Color.BLUE);
        lblRentHour.setBounds(400, 370, 100, 40);
        add(lblRentHour);

        lblDate = new JLabel("Date ");
        lblDate.setForeground(Color.BLUE);
        lblDate.setBounds(400, 430, 100, 40);
        add(lblDate);

        lblDueDate = new JLabel("End Date ");
        lblDueDate.setForeground(Color.BLUE);
        lblDueDate.setBounds(400, 490, 100, 40);
        add(lblDueDate);

        // Vehicle dropdown — loads ALL cars from DB
        cmbVehicle = new JComboBox<>();
        cmbVehicle.setBounds(550, 130, 200, 40);
        add(cmbVehicle);

        // Available field — read-only, driven by selected vehicle's status
        txtAvailable = new JTextField();
        txtAvailable.setBackground(new Color(240, 240, 244));
        txtAvailable.setBounds(550, 190, 200, 40);
        txtAvailable.setEditable(false);
        add(txtAvailable);

        // Customer dropdown — loads all customers from DB
        cmbCustomer = new JComboBox<>();
        cmbCustomer.setBackground(new Color(240, 240, 244));
        cmbCustomer.setBounds(550, 250, 200, 40);
        add(cmbCustomer);

        // Rent fee — read-only, auto-filled from selected vehicle's rental_price
        txtRentFee = new JTextField();
        txtRentFee.setBackground(new Color(240, 240, 244));
        txtRentFee.setBounds(550, 310, 200, 40);
        txtRentFee.setEditable(false);
        add(txtRentFee);

        // Rental hour — only "12" or empty
        txtRentHour = new JTextField();
        txtRentHour.setBackground(new Color(240, 240, 244));
        txtRentHour.setBounds(550, 370, 200, 40);
        add(txtRentHour);

        txtDate = new JTextField();
        txtDate.setBackground(new Color(240, 240, 244));
        txtDate.setBounds(550, 430, 200, 40);
        add(txtDate);

        txtDueDate = new JTextField();
        txtDueDate.setBackground(new Color(240, 240, 244));
        txtDueDate.setBounds(550, 490, 200, 40);
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
        loadTableData();
        scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(177, 218, 220));
        scrollPane.setBounds(800, 130, 500, 500);
        add(scrollPane);

        panel = new JPanel();
        panel.setBackground(new Color(245, 245, 220));
        panel.setBounds(300, 0, 1070, 700);
        add(panel);

        // When the user changes the vehicle selection, update Available and Rental Fee
        cmbVehicle.addActionListener(e -> onVehicleSelected());

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
        scrollPane.setVisible(visible);
        btnCancel.setVisible(visible);
        btnDelete.setVisible(visible);
        btnEdit.setVisible(visible);
        btnAdd.setVisible(visible);
        lblVehicle.setVisible(visible);
        lblCustomer.setVisible(visible);
        lblAvailable.setVisible(visible);
        lblDate.setVisible(visible);
        lblDueDate.setVisible(visible);
        lblHeader.setVisible(visible);
        lblRentFee.setVisible(visible);
        lblRentHour.setVisible(visible);
        cmbCustomer.setVisible(visible);
        txtAvailable.setVisible(visible);
        txtDate.setVisible(visible);
        txtDueDate.setVisible(visible);
        txtRentFee.setVisible(visible);
        txtRentHour.setVisible(visible);
        cmbVehicle.setVisible(visible);
    }

    public void showRentalInvoice() {
        loadCars();
        loadCustomers();
        setAllVisible(true);
    }

    // -----------------------------------------------------------------------
    // Load data from DB
    // -----------------------------------------------------------------------

    /**
     * Loads ALL registered vehicles into the vehicle dropdown.
     * Each entry shows: Color - Make - Model
     * The CarItem also holds rental_price and available status.
     */
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

    /**
     * Loads all customers into the customer dropdown.
     * Shows customer names.
     */
    private void loadCustomers() {
        cmbCustomer.removeAllItems();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT customer_name FROM customers ORDER BY customer_name";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cmbCustomer.addItem(rs.getString("customer_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Called whenever the vehicle combo selection changes.
     * Updates the Available text field and auto-fills the rental fee.
     * "Yes"          → car.available is "Available"
     * "No"           → car.available is "Rented"
     * "Maintenance"  → car.available is "Maintenance"
     */
    private void onVehicleSelected() {
        if (isEditing) return;

        CarItem selected = (CarItem) cmbVehicle.getSelectedItem();
        if (selected == null) {
            txtAvailable.setText("");
            txtRentFee.setText("");
            return;
        }

        // Map DB status to display label
        String status = selected.getAvailable();
        if (status == null) status = "";

        switch (status.trim()) {
            case "Available":
                txtAvailable.setText("Yes");
                break;
            case "Rented":
                txtAvailable.setText("No");
                break;
            case "Maintenance":
                txtAvailable.setText("Maintenance");
                break;
            default:
                txtAvailable.setText(status);
                break;
        }

        // Auto-fill rental fee from the vehicle's registered price (not editable)
        txtRentFee.setText(String.format("%.2f", selected.getRentalPrice()));

        // Only allow adding if the car is Available
        boolean canRent = "Available".equalsIgnoreCase(status.trim());
        btnAdd.setEnabled(canRent);
        txtRentHour.setEnabled(canRent);
        txtDate.setEnabled(canRent);
        txtDueDate.setEnabled(canRent);
        cmbCustomer.setEnabled(canRent);

        if (!canRent) {
            String reason = "No".equals(txtAvailable.getText())
                    ? "This car is currently rented."
                    : "This car is currently under maintenance.";
            JOptionPane.showMessageDialog(this, reason, "Unavailable", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadTableData() {
        model.setRowCount(0);
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM rentals";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("car_id"),
                    rs.getString("customer_name"),
                    rs.getDouble("rental_fee"),
                    rs.getString("rental_hour"),
                    rs.getString("start_date"),
                    rs.getString("end_date")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            clearFields();
            JOptionPane.showMessageDialog(null, "Fields Cleared!");

        } else if (e.getSource() == btnDelete) {
            handleDelete();

        } else if (e.getSource() == btnEdit) {
            handleEdit();

        } else if (e.getSource() == btnAdd) {
            handleAdd();
        }
    }

    private void clearFields() {
        txtRentHour.setText("");
        txtDate.setText("");
        txtDueDate.setText("");
        // Re-trigger vehicle selection to reset fee and availability
        onVehicleSelected();
    }

    private void handleDelete() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to remove.", "Delete", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int choice = JOptionPane.showConfirmDialog(null,
                "Do you want to remove this record?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Operation Canceled.");
            return;
        }
        try {
            String carID = model.getValueAt(selectedRow, 0).toString();
            String customerName = model.getValueAt(selectedRow, 1).toString();
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM rentals WHERE car_id=? AND customer_name=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, carID);
            pst.setString(2, customerName);
            pst.executeUpdate();
            model.removeRow(selectedRow);
            JOptionPane.showMessageDialog(null, "Record Deleted Successfully!", "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting record!");
        }
    }

    private void handleEdit() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to edit.", "Update", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isEditing) {
            // Enter edit mode — populate fields from the selected row
            isEditing = true;
            String carIdStr = model.getValueAt(selectedRow, 0).toString();
            // Select the matching CarItem in the dropdown
            for (int i = 0; i < cmbVehicle.getItemCount(); i++) {
                CarItem item = cmbVehicle.getItemAt(i);
                if (String.valueOf(item.getCarId()).equals(carIdStr)) {
                    cmbVehicle.setSelectedIndex(i);
                    break;
                }
            }
            cmbCustomer.setSelectedItem(model.getValueAt(selectedRow, 1).toString());
           
            txtRentFee.setText(model.getValueAt(selectedRow, 2).toString());
            txtRentHour.setText(model.getValueAt(selectedRow, 3).toString());
            txtDate.setText(model.getValueAt(selectedRow, 4).toString());
            txtDueDate.setText(model.getValueAt(selectedRow, 5).toString());
            btnAdd.setEnabled(false);
            JOptionPane.showMessageDialog(null, "You can now edit the fields. Click Edit again to save.");

        } else {
            // Save edited values
            String rentHourInput = txtRentHour.getText().trim();
            if (!rentHourInput.isEmpty() && !rentHourInput.equals("12")) {
                JOptionPane.showMessageDialog(this, "Rental Hour must be 12 or empty.", "Update", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                CarItem selectedCar = (CarItem) cmbVehicle.getSelectedItem();
                if (selectedCar == null) return;
                String carID = String.valueOf(selectedCar.getCarId());
                String customerName = (String) cmbCustomer.getSelectedItem();
                double fee = Double.parseDouble(txtRentFee.getText().trim());
                LocalDate startDate = LocalDate.parse(txtDate.getText().trim(), formatter);
                LocalDate dueDate = LocalDate.parse(txtDueDate.getText().trim(), formatter);

                if (dueDate.isBefore(startDate)) {
                    JOptionPane.showMessageDialog(this, "End date cannot be before start date.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                long daysBetween = ChronoUnit.DAYS.between(startDate, dueDate);
                if (daysBetween == 0 && rentHourInput.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Same day rental requires Rental Hour = 12.", "Update", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Connection con = DBConnection.getConnection();
                String sql = "UPDATE rentals SET customer_name=?, rental_fee=?, rental_hour=?, "
                        + "start_date=?, due_date=? WHERE car_id=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, customerName);
                pst.setDouble(2, fee);
                if (rentHourInput.isEmpty()) {
                    pst.setNull(3, java.sql.Types.INTEGER);
                } else {
                    pst.setInt(3, Integer.parseInt(rentHourInput));
                }
                pst.setDate(4, java.sql.Date.valueOf(startDate));
                pst.setDate(5, java.sql.Date.valueOf(dueDate));
                pst.setString(6, carID);
                pst.executeUpdate();

                model.setValueAt(carID, selectedRow, 0);
                model.setValueAt(customerName, selectedRow, 1);
                model.setValueAt(txtRentFee.getText(), selectedRow, 2);
                model.setValueAt(rentHourInput, selectedRow, 3);
                model.setValueAt(txtDate.getText(), selectedRow, 4);
                model.setValueAt(txtDueDate.getText(), selectedRow, 5);

                JOptionPane.showMessageDialog(null, "Transaction Updated Successfully!");

                isEditing = false;
                btnAdd.setEnabled(true);
                table.clearSelection();
                clearFields();

            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format! Please use MM/dd/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric fee.", "Update", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating record!");
            }
        }
    }

    private void handleAdd() {
        CarItem selectedCar = (CarItem) cmbVehicle.getSelectedItem();
        if (selectedCar == null) {
            JOptionPane.showMessageDialog(this, "Please select a vehicle.", "Add", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String carId = String.valueOf(selectedCar.getCarId());
        String customerName = (String) cmbCustomer.getSelectedItem();
        String rentFee = txtRentFee.getText().trim();
        String rentHour = txtRentHour.getText().trim();
        String startDateStr = txtDate.getText().trim();
        String dueDateStr = txtDueDate.getText().trim();

       
        if (customerName == null || customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a customer.", "Add", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (startDateStr.isEmpty() || dueDateStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Date and End Date are required.", "Add", JOptionPane.ERROR_MESSAGE);
            return;
        }

      
        if (!rentHour.isEmpty() && !rentHour.equals("12")) {
            JOptionPane.showMessageDialog(this, "Rental Hour must be 12 or leave it empty.", "Add", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double fee = Double.parseDouble(rentFee);
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);

            if (dueDate.isBefore(startDate)) {
                JOptionPane.showMessageDialog(this, "End date cannot be before start date.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

           
            long daysBetween = ChronoUnit.DAYS.between(startDate, dueDate);
            if (daysBetween == 0 && rentHour.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Same day rental requires Rental Hour = 12.", "Add", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!isCarAvailableForDates(carId, startDate, dueDate)) {
                JOptionPane.showMessageDialog(this,
                        "This car is already rented during the selected dates.",
                        "Unavailable", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "INSERT INTO rentals (car_id, customer_name, rental_fee, rental_hour, start_date, end_date, customer_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, carId);
                pst.setString(2, customerName);
                pst.setDouble(3, fee);
                if (rentHour.isEmpty()) {
                    pst.setNull(4, java.sql.Types.INTEGER);
                } else {
                    pst.setInt(4, Integer.parseInt(rentHour));
                }
                pst.setDate(5, java.sql.Date.valueOf(startDate));
                pst.setDate(6, java.sql.Date.valueOf(dueDate));
                pst.setString(7, customerName);

//                // Compute total price using availableManager logic
//                String total = availableManager.computeTotal(rentFee, rentHour,
//                        startDate.format(formatter), dueDate.format(formatter));
//                pst.setDouble(8, Double.parseDouble(total.equals("N/A") ? "0" : total));

                int rowsInserted = pst.executeUpdate();
                if (rowsInserted > 0) {
                    model.addRow(new Object[]{
                        carId,
                        customerName,
                        rentFee,
                        rentHour,
                        startDate.format(formatter),
                        dueDate.format(formatter)
                    });
                    JOptionPane.showMessageDialog(null, "Rental Saved Successfully!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Database rejected insertion. Check constraints.");
                }
            }

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format! Please use MM/dd/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric fee.", "Add", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
        }
    }

    
    private boolean isCarAvailableForDates(String carId, LocalDate startDate, LocalDate dueDate) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM rentals WHERE car_id = ? "
                    + "AND start_date <= ? AND end_date >= ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, carId);
            pst.setDate(2, java.sql.Date.valueOf(dueDate));
            pst.setDate(3, java.sql.Date.valueOf(startDate));
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}

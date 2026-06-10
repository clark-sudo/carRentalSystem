/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.carrentalsystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
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
public class calendarManagement extends JFrame implements ActionListener {

    private JLabel lblHeader, lblCarID, lblCustomerID, lblCustomer, lblRentFee, lblRentHour, lblDate, lblDueDate;
    private JButton btnAdd, btnUpdate, btnDelete, btnCancel;
    private JTextField txtCustomer, txtRentFee, txtRentHour, txtDate, txtDueDate;
    private JComboBox<String> cmbCarID, cmbCustomerID;
    private JTable tblManagement;
    private JScrollPane spTable;
    private DefaultTableModel model;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static ArrayList<CalendarInMemory> rentalList = new ArrayList<>();
    private boolean isEditing = false;

    calendarManagement() {
        getContentPane().setBackground(new Color(245, 245, 220));
        setSize(1200, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblHeader = new JLabel("Calendar");
        lblHeader.setForeground(Color.BLUE);
        lblHeader.setBounds(50, 50, 100, 30);
        add(lblHeader);

        lblCarID = new JLabel("Car ID ");
        lblCarID.setForeground(Color.BLUE);
        lblCarID.setBounds(100, 130, 100, 40);
        add(lblCarID);

        lblCustomerID = new JLabel("Customer ID ");
        lblCustomerID.setForeground(Color.BLUE);
        lblCustomerID.setBounds(100, 190, 100, 40);
        add(lblCustomerID);

        lblCustomer = new JLabel("Customer Name ");
        lblCustomer.setForeground(Color.BLUE);
        lblCustomer.setBounds(100, 250, 100, 40);
        add(lblCustomer);

        lblRentFee = new JLabel("Rental Fee ");
        lblRentFee.setForeground(Color.BLUE);
        lblRentFee.setBounds(100, 310, 100, 40);
        add(lblRentFee);

        lblRentHour = new JLabel("Rental Hour ");
        lblRentHour.setForeground(Color.BLUE);
        lblRentHour.setBounds(100, 370, 100, 40);
        add(lblRentHour);

        lblDate = new JLabel("Date ");
        lblDate.setForeground(Color.BLUE);
        lblDate.setBounds(100, 430, 100, 40);
        add(lblDate);

        lblDueDate = new JLabel("Due Date ");
        lblDueDate.setForeground(Color.BLUE);
        lblDueDate.setBounds(100, 490, 100, 40);
        add(lblDueDate);

        cmbCarID = new JComboBox<>();
        cmbCarID.setBounds(250, 130, 200, 40);
        add(cmbCarID);
        loadCarIDs();

        cmbCustomerID = new JComboBox<>();
        cmbCustomerID.setBounds(250, 190, 200, 40);
        add(cmbCustomerID);
        loadCustomerIDs();

        txtCustomer = new JTextField();
        txtCustomer.setEditable(false);
        txtCustomer.setBounds(250, 250, 200, 40);
        add(txtCustomer);

        txtRentFee = new JTextField();
        txtRentFee.setEditable(false);
        txtRentFee.setBounds(250, 310, 200, 40);
        add(txtRentFee);

        txtRentHour = new JTextField();
        txtRentHour.setBounds(250, 370, 200, 40);
        add(txtRentHour);

        txtDate = new JTextField();
        txtDate.setBounds(250, 430, 200, 40);
        add(txtDate);

        txtDueDate = new JTextField();
        txtDueDate.setBounds(250, 490, 200, 40);
        add(txtDueDate);

        btnAdd = new JButton("Add");
        btnAdd.setBackground(new Color(0, 130, 120));
        btnAdd.setForeground(Color.white);
        btnAdd.setBounds(150, 560, 100, 40);
        add(btnAdd);

        btnUpdate = new JButton("Edit");
        btnUpdate.setBackground(new Color(0, 130, 120));
        btnUpdate.setForeground(Color.white);
        btnUpdate.setBounds(350, 560, 100, 40);
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBackground(new Color(0, 130, 120));
        btnDelete.setForeground(Color.white);
        btnDelete.setBounds(150, 630, 100, 40);
        add(btnDelete);

        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(new Color(0, 130, 120));
        btnCancel.setForeground(Color.white);
        btnCancel.setBounds(350, 630, 100, 40);
        add(btnCancel);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
            "Car ID", "Customer ID", "Customer Name",
            "Rental Fee", "Rental Hour", "Date", "Due Date", "Total Price"
        });

        tblManagement = new JTable(model);
        loadTableData();
        spTable = new JScrollPane(tblManagement);
        spTable.setBounds(500, 100, 650, 500);
        add(spTable);

        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnCancel.addActionListener(this);
        cmbCarID.addActionListener(this);
        cmbCustomerID.addActionListener(this);

        updateFieldStatus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            dispose();
            homePage hp = new homePage();
            hp.setVisible(true);

        } else if (e.getSource() == btnDelete) {

            int selectedRow = tblManagement.getSelectedRow();

            if (selectedRow != -1) {

                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Do you want to remove this record?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {

                    try {

                        String carID = model.getValueAt(selectedRow, 0).toString();
                        String customerID = model.getValueAt(selectedRow, 1).toString();

                        Connection con = DBConnection.getConnection();

                        String sql = "DELETE FROM rentals WHERE car_id=? AND customer_id=?";

                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.setString(1, carID);
                        pst.setString(2, customerID);

                        pst.executeUpdate();

                        model.removeRow(selectedRow);

                        JOptionPane.showMessageDialog(null, "Record Deleted Successfully!");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error deleting record!");
                    }

                }

            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            }

            isEditing = false;
            tblManagement.clearSelection();
            updateFieldStatus();
        } else if (e.getSource() == btnUpdate) {
            int selectedRow = tblManagement.getSelectedRow();
            if (selectedRow != -1) {

                // First click on Edit — populate fields from selected row
                if (!isEditing) {
                    isEditing = true;
                    cmbCarID.setSelectedItem(model.getValueAt(selectedRow, 0));
                    cmbCustomerID.setSelectedItem(model.getValueAt(selectedRow, 1));
                    txtCustomer.setText(model.getValueAt(selectedRow, 2).toString());
                    txtRentFee.setText(model.getValueAt(selectedRow, 3).toString());
                    txtRentHour.setText(model.getValueAt(selectedRow, 4).toString());
                    txtDate.setText(model.getValueAt(selectedRow, 5).toString());
                    txtDueDate.setText(model.getValueAt(selectedRow, 6).toString());
                    // Enable all fields so user can make changes
                    cmbCustomerID.setEnabled(true);
                    txtCustomer.setEnabled(true);
                    txtRentFee.setEnabled(true);
                    txtRentHour.setEnabled(true);
                    txtDate.setEnabled(true);
                    txtDueDate.setEnabled(true);
                    btnAdd.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "You can now edit the fields. Click Edit again to save.");

                } else {
                    String rentHourInput = txtRentHour.getText().trim();

                    if (!rentHourInput.isEmpty() && !rentHourInput.equals("12")) {
                        JOptionPane.showMessageDialog(this, "Rental Hour must be 12 or empty.");
                        return;
                    }

                    try {
                        String carID = cmbCarID.getSelectedItem().toString();
                        String customerID = cmbCustomerID.getSelectedItem().toString();

                        // Parse the edited dates
                        LocalDate startDate = LocalDate.parse(txtDate.getText().trim(), formatter);
                        LocalDate dueDate = LocalDate.parse(txtDueDate.getText().trim(), formatter);

                        if (dueDate.isBefore(startDate)) {
                            JOptionPane.showMessageDialog(this, "Error: due date cannot be before start date.");
                            return;
                        }

                        // Recalculate the price by instantiating a temporary CalendarInMemory object
                        CalendarInMemory updatedCim = new CalendarInMemory(
                                carID,
                                customerID,
                                txtCustomer.getText(),
                                txtRentFee.getText(),
                                rentHourInput,
                                startDate.format(formatter),
                                dueDate.format(formatter)
                        );

                        // Parse the freshly calculated total price
                        double newTotalPrice = Double.parseDouble(updatedCim.getTotalPrice().replace(",", ""));

                        Connection con = DBConnection.getConnection();

                        // 1. ADDED total_price=? TO THE SQL UPDATE STRING
                        String sql = "UPDATE rentals SET customer_id=?, customer_name=?, rental_fee=?, "
                                + "rental_hour=?, start_date=?, due_date=?, total_price=? WHERE car_id=?";

                        PreparedStatement pst = con.prepareStatement(sql);

                        pst.setString(1, customerID);
                        pst.setString(2, txtCustomer.getText());
                        pst.setDouble(3, Double.parseDouble(txtRentFee.getText()));

                        // Handle the empty hour bug safely
                        if (rentHourInput.isEmpty()) {
                            pst.setNull(4, java.sql.Types.INTEGER);
                        } else {
                            pst.setInt(4, Integer.parseInt(rentHourInput));
                        }

                        pst.setDate(5, java.sql.Date.valueOf(startDate));
                        pst.setDate(6, java.sql.Date.valueOf(dueDate));
                        pst.setDouble(7, newTotalPrice); // 2. SET THE NEW TOTAL PRICE HERE
                        pst.setString(8, carID);

                        pst.executeUpdate();

                        // 3. UPDATE THE JTABLE COLUMNS (Including total price at column index 7)
                        model.setValueAt(carID, selectedRow, 0);
                        model.setValueAt(customerID, selectedRow, 1);
                        model.setValueAt(txtCustomer.getText(), selectedRow, 2);
                        model.setValueAt(txtRentFee.getText(), selectedRow, 3);
                        model.setValueAt(rentHourInput, selectedRow, 4);
                        model.setValueAt(txtDate.getText(), selectedRow, 5);
                        model.setValueAt(txtDueDate.getText(), selectedRow, 6);
                        model.setValueAt(updatedCim.getTotalPrice(), selectedRow, 7); // Sets the new formatted total

                        JOptionPane.showMessageDialog(null, "Record Updated Successfully!");

                        // Reset editing state and fields
                        isEditing = false;
                        tblManagement.clearSelection();
                        cmbCustomerID.setSelectedIndex(0);
                        txtCustomer.setText("");
                        txtRentFee.setText("");
                        txtRentHour.setText("");
                        txtDate.setText("");
                        txtDueDate.setText("");
                        btnAdd.setEnabled(true);

                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid date format! Use MM/dd/yyyy");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error updating record!");
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.");
            }

        } else if (e.getSource() == btnAdd) {
            String carID = cmbCarID.getSelectedItem().toString();
            String customerID = cmbCustomerID.getSelectedItem().toString();
            String customerName = txtCustomer.getText();
            String rentFee = txtRentFee.getText();
            String rentHour = txtRentHour.getText().trim();
            String startDateStr = txtDate.getText().trim();
            String dueDateStr = txtDueDate.getText().trim();

            // Rent hour must be 12 or empty, nothing else
            if (!rentHour.isEmpty() && !rentHour.equals("12")) {
                JOptionPane.showMessageDialog(this, "Rental Hour must be 12 or leave it empty.");
                return;
            }

            try {
                // Parse dates using the MM/dd/yyyy formatter
                LocalDate startDate = LocalDate.parse(startDateStr, formatter);
                LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);

                if (dueDate.isBefore(startDate)) {
                    JOptionPane.showMessageDialog(this, "Error: due date cannot be before start date.");
                    return;
                }

                // Same day with no hours means nothing to charge
                long daysBetween = ChronoUnit.DAYS.between(startDate, dueDate);
                if (daysBetween == 0 && rentHour.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Same day rental requires 12 hours to be entered.");
                    return;
                }

                CalendarInMemory cim = new CalendarInMemory(
                        carID,
                        customerID,
                        customerName,
                        rentFee,
                        rentHour,
                        startDate.format(formatter),
                        dueDate.format(formatter)
                );

                // Use Try-With-Resources to guarantee the statement closes and commits cleanly
                String sql = "INSERT INTO rentals (car_id, customer_id, customer_name, rental_fee, rental_hour, start_date, due_date, total_price) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {

                    pst.setString(1, carID);
                    pst.setString(2, customerID);
                    pst.setString(3, customerName);
                    pst.setDouble(4, Double.parseDouble(rentFee));

                    // Safely handle empty input for an integer database column
                    if (rentHour.isEmpty()) {
                        pst.setNull(5, java.sql.Types.INTEGER); // Saves as NULL in DB for daily rentals
                        // ALTERNATIVE: pst.setInt(5, 0);       // Use this if your DB doesn't allow NULLs
                    } else {
                        pst.setInt(5, Integer.parseInt(rentHour));
                    }

                    pst.setDate(6, java.sql.Date.valueOf(startDate));
                    pst.setDate(7, java.sql.Date.valueOf(dueDate));

                    // Safe numeric formatting for database storage
                    double total = Double.parseDouble(cim.getTotalPrice().replace(",", ""));
                    pst.setDouble(8, total);

                    int rowsInserted = pst.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Rental Saved Successfully to Database!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Database rejected insertion. Check constraints.");
                        return; // Stop UI update if DB write failed
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
                    return; // Stop UI update if DB write failed
                }

                // Update in-memory collections and UI elements only if DB save succeeded
                for (CarInMemory car : carRentals.carList) {
                    if (car.getCarID().equals(carID)) {
                        car.setAvailability("No");
                        break;
                    }
                }

                model.addRow(new Object[]{
                    carID,
                    customerID,
                    customerName,
                    rentFee,
                    rentHour,
                    startDate.format(formatter),
                    dueDate.format(formatter),
                    cim.getTotalPrice()
                });

                // Clear input controls for next input session
                cmbCustomerID.setSelectedIndex(0);
                txtCustomer.setText("");
                txtRentFee.setText("");
                txtRentHour.setText("");
                txtDate.setText("");
                txtDueDate.setText("");

            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format! Please use MM/dd/yyyy.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid numeric value found in fee or total pricing calculations.");
            }
        } else if (e.getSource() == cmbCarID) {
            if (!isEditing) {
                updateFieldStatus();
            }
        } else if (e.getSource() == cmbCustomerID) {
            updateCustomerInfo();
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
                    rs.getString("customer_id"),
                    rs.getString("customer_name"),
                    rs.getDouble("rental_fee"),
                    rs.getString("rental_hour"),
                    rs.getString("start_date"),
                    rs.getString("due_date"),
                    rs.getDouble("total_price")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCarIDs() {

        cmbCarID.removeAllItems();

        try {

            Connection con = DBConnection.getConnection();

            String sql
                    = "SELECT car_id FROM cars WHERE available = 'Yes'";

            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                cmbCarID.addItem(
                        rs.getString("car_id")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerIDs() {

        cmbCustomerID.removeAllItems();

        try {

            Connection con = DBConnection.getConnection();

            String sql
                    = "SELECT customer_id FROM customers";

            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                cmbCustomerID.addItem(
                        rs.getString("customer_id")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateFieldStatus() {

        if (isEditing) {
            return;
        }

        String selectedCarID
                = (String) cmbCarID.getSelectedItem();

        if (selectedCarID == null) {
            txtRentFee.setText("");
            return;
        }

        try {

            Connection con = DBConnection.getConnection();

            String sql
                    = "SELECT rental_price, available "
                    + "FROM cars "
                    + "WHERE car_id = ?";

            PreparedStatement pst
                    = con.prepareStatement(sql);

            pst.setString(1, selectedCarID);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                txtRentFee.setText(
                        rs.getString("rental_price")
                );

                String availability
                        = rs.getString("available");

                boolean available
                        = availability.equalsIgnoreCase("Yes");

                cmbCustomerID.setEnabled(available);
                txtCustomer.setEnabled(available);
                txtRentFee.setEnabled(available);
                txtRentHour.setEnabled(available);
                txtDate.setEnabled(available);
                txtDueDate.setEnabled(available);
                btnAdd.setEnabled(available);

                if (!available) {
                    JOptionPane.showMessageDialog(
                            this,
                            "This car is currently unavailable."
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCustomerInfo() {

        String selectedCustomerID
                = (String) cmbCustomerID.getSelectedItem();

        if (selectedCustomerID == null) {
            return;
        }

        try {

            Connection con = DBConnection.getConnection();

            String sql
                    = "SELECT customer_name "
                    + "FROM customers "
                    + "WHERE customer_id = ?";

            PreparedStatement pst
                    = con.prepareStatement(sql);

            pst.setString(1, selectedCustomerID);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                txtCustomer.setText(
                        rs.getString("customer_name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package org.example.gui;
import org.example.controllers.VendorController;
import org.example.models.Vendor;
import org.example.tables.VendorTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class VendorPanel extends JPanel {

    private JTable vendorTable;
    private VendorTableModel vendorTableModel;
    private JButton createVendorButton;
    private JButton editVendorButton;
    private JButton removeVendorButton;
    private JLabel vendorCountLabel;

    private VendorController vendorController;

    public VendorPanel(VendorController vendorController) {
        this.vendorController = vendorController;
        initializeUI();
        setTableData(vendorController.getAllVendors());
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        vendorTableModel = new VendorTableModel();
        vendorTable = new JTable(vendorTableModel);
        JScrollPane scrollPane = new JScrollPane(vendorTable);
        add(scrollPane, BorderLayout.CENTER);

        createVendorButton = new JButton("Create Vendor");
        createVendorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createVendor();
            }
        });

        editVendorButton = new JButton("Edit Vendor");
        editVendorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editVendor();
            }
        });

        removeVendorButton = new JButton("Remove Vendor");
        removeVendorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeVendor();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createVendorButton);
        buttonPanel.add(editVendorButton);
        buttonPanel.add(removeVendorButton);

        add(buttonPanel, BorderLayout.PAGE_END);

        vendorCountLabel = new JLabel("Vendors Count: 0");


        vendorTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editVendor();
                }
            }
        });
    }

    public void setTableData(List<Vendor> vendors) {
        vendorTableModel.setVendors(vendors);
        vendorTableModel.fireTableDataChanged();
        updateVendorsCountLabel();
    }

    private void createVendor() {
        // Similar logic as in UserPanel
        String vendorName = JOptionPane.showInputDialog(this, "Enter vendor name:");
        if (vendorName != null && !vendorName.isEmpty()) {
            vendorController.createVendor(new Vendor(vendorName));
            setTableData(vendorController.getAllVendors());
        } else {
            JOptionPane.showMessageDialog(this, "Vendor name cannot be empty.");
        }
    }

    private void editVendor() {
        // Similar logic as in UserPanel
        int selectedRow = vendorTable.getSelectedRow();
        if (selectedRow != -1) {
            Vendor selectedVendor = vendorTableModel.getVendorAt(selectedRow);
            String newVendorName = JOptionPane.showInputDialog(this, "Enter new vendor name:", selectedVendor.getName());
            if (newVendorName != null && !newVendorName.isEmpty()) {
                selectedVendor.setName(newVendorName);
                vendorController.updateVendor(selectedVendor);
                setTableData(vendorController.getAllVendors());
            } else {
                JOptionPane.showMessageDialog(this, "Vendor name cannot be empty.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a vendor to edit.");
        }
    }

    private void removeVendor() {
        // Similar logic as in UserPanel
        int selectedRow = vendorTable.getSelectedRow();
        if (selectedRow != -1) {
            Vendor selectedVendor = vendorTableModel.getVendorAt(selectedRow);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this vendor?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                vendorController.removeVendor(selectedVendor);
                setTableData(vendorController.getAllVendors());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a vendor to remove.");
        }
    }

    private void updateVendorsCountLabel() {
        int vendorCount = vendorTableModel.getRowCount();
        vendorCountLabel.setText("Vendors Count: " + vendorCount);
    }
}

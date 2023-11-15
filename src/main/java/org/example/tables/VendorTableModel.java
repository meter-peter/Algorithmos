package org.example.tables;

import org.example.models.Vendor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class VendorTableModel extends AbstractTableModel {

    private List<Vendor> vendors;
    private String[] columnNames = {"ID", "Vendor Name"};

    public VendorTableModel() {
        this.vendors = new ArrayList<>();
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    @Override
    public int getRowCount() {
        return vendors.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vendor vendor = vendors.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return vendor.getId();
            case 1:
                return vendor.getName();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Vendor getVendorAt(int rowIndex) {
        return vendors.get(rowIndex);
    }
}

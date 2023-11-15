package org.example.controllers;

import org.example.dao.VendorDao;
import org.example.models.Vendor;
import org.example.gui.VendorPanel;

import java.util.List;

public class VendorController {

    private VendorDao vendorDao;

    public VendorController(VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }

    public List<Vendor> getAllVendors() {
        return vendorDao.getAllVendors();
    }

    public void createVendor(Vendor vendor) {
        vendorDao.saveVendor(vendor);
    }

    public void updateVendor(Vendor vendor) {
        vendorDao.updateVendor(vendor);
    }

    public void removeVendor(Vendor vendor) {
        vendorDao.deleteVendor(vendor);
    }
}

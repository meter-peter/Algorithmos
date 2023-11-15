package org.example.gui;

import org.example.controllers.ProductController;
import org.example.controllers.UserController;
import org.example.controllers.VendorController;
import org.example.dao.ProductDao;
import org.example.dao.UserDao;
import org.example.dao.VendorDao;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTabbedPane tabbedPane;

    public MainFrame() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Inventory Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Vendors",createVendorsPanel());
        tabbedPane.addTab("Users", createUserPanel());
        tabbedPane.addTab("Products", createProductPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createUserPanel() {
        UserController userController = new UserController(new UserDao());
        UserPanel userPanel = new UserPanel(userController);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(userPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createProductPanel() {
        ProductController productController = new ProductController(new ProductDao());
        ProductPanel productPanel = new ProductPanel(productController);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(productPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createVendorsPanel(){
        VendorController vendorController = new VendorController(new VendorDao());
        VendorPanel vendorPanel = new VendorPanel(vendorController);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(vendorPanel,BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}

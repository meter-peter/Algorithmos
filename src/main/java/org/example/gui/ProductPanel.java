package org.example.gui;

import org.example.controllers.ProductController;
import org.example.models.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductPanel extends JPanel {

    private JList<Product> productList;
    private DefaultListModel<Product> productListModel;
    private JButton createProductButton;
    private JButton editProductButton;
    private JButton deleteProductButton;

    private ProductController productController;

    public ProductPanel(ProductController productController) {
        this.productController = productController;
        initializeUI();
        setListData(productController.getAllProducts());
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        productListModel = new DefaultListModel<>();
        productList = new JList<>(productListModel);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        productList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JList<?> list = (JList<?>) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    editProduct(productListModel.getElementAt(index));
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(productList);
        add(scrollPane, BorderLayout.CENTER);

        createProductButton = new JButton("Create Product");
        createProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createProduct();
            }
        });

        editProductButton = new JButton("Edit Product");
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = productList.getSelectedIndex();
                if (selectedIndex != -1) {
                    editProduct(productListModel.get(selectedIndex));
                } else {
                    JOptionPane.showMessageDialog(ProductPanel.this, "Please select a product to edit.");
                }
            }
        });

        deleteProductButton = new JButton("Delete Product");
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createProductButton);
        buttonPanel.add(editProductButton);
        buttonPanel.add(deleteProductButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setListData(List<Product> products) {
        productListModel.clear();
        for (Product product : products) {
            productListModel.addElement(product);
        }
    }

    private void createProduct() {
        // Show a dialog to create a new product
        Product newProduct = new Product();
        ProductEditDialog editDialog = new ProductEditDialog((Frame) SwingUtilities.getWindowAncestor(this), productController, newProduct);
        editDialog.setVisible(true);
        // Refresh the list data after creating a product
        setListData(productController.getAllProducts());
    }

    private void editProduct(Product product) {
        // Show a dialog to edit the selected product
        ProductEditDialog editDialog = new ProductEditDialog((Frame) SwingUtilities.getWindowAncestor(this), productController, product);
        editDialog.setVisible(true);
        // Refresh the list data after editing a product
        setListData(productController.getAllProducts());
    }

    private void deleteProduct() {
        int selectedIndex = productList.getSelectedIndex();
        if (selectedIndex != -1) {
            Product selectedProduct = productListModel.get(selectedIndex);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this product?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                productController.deleteProduct(selectedProduct);
                setListData(productController.getAllProducts());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to remove.");
        }
    }
}

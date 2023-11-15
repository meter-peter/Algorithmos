package org.example.gui;

import org.example.controllers.ProductController;
import org.example.models.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductEditDialog extends JDialog {

    private JTextField nameField;
    private JTextArea descriptionArea;
    private JTextField purchasePriceField;
    private JTextField salePriceField;
    private JButton saveButton;
    private JButton uploadImageButton;
    private JLabel imageLabel;

    private ProductController productController;
    private Product currentProduct;

    public ProductEditDialog(Frame owner, ProductController productController, Product product) {
        super(owner, "Edit Product", true);
        this.productController = productController;
        this.currentProduct = product;

        initializeUI();
        populateFields(product);
    }

    private void initializeUI() {
        setLayout(new FlowLayout());

        nameField = new JTextField(20);
        descriptionArea = new JTextArea(5, 20);
        purchasePriceField = new JTextField(20);
        salePriceField = new JTextField(20);
        saveButton = new JButton("Save");
        uploadImageButton = new JButton("Upload Image");
        imageLabel = new JLabel();

        add(new JLabel("Name"));
        add(nameField);
        add(new JLabel("Description"));
        add(descriptionArea);
        add(new JLabel("Purchase Price"));
        add(purchasePriceField);
        add(new JLabel("Sale Price"));
        add(salePriceField);
        add(new JLabel("Image"));
        add(imageLabel);
        add(saveButton);
        add(uploadImageButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });

        uploadImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadImage();
            }
        });

        setSize(400, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getOwner());
    }

    private void populateFields(Product product) {
        nameField.setText(product.getName());
        descriptionArea.setText(product.getDescription());
        purchasePriceField.setText(String.valueOf(product.getPurchasePrice()));
        salePriceField.setText(String.valueOf(product.getSalePrice()));
        // Set image from product.getImage() to the imageLabel
        // This depends on how you store and retrieve images
    }

    private void saveChanges() {
        // Update the currentProduct with the changes from the fields
        currentProduct.setName(nameField.getText());
        currentProduct.setDescription(descriptionArea.getText());
        currentProduct.setPurchasePrice(Double.parseDouble(purchasePriceField.getText()));
        currentProduct.setSalePrice(Double.parseDouble(salePriceField.getText()));
        // Save the updated product using productController
        productController.updateProduct(currentProduct);
        // Close the dialog
        dispose();
    }

    private void uploadImage() {
        // Implement image upload logic, and update the imageLabel
        // This might involve using a file chooser and handling image files
    }
}

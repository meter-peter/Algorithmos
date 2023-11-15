package org.example.gui;

import org.example.controllers.ProductController;
import org.example.models.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
        setLayout(new BorderLayout());

        nameField = new JTextField();
        descriptionArea = new JTextArea();
        purchasePriceField = new JTextField();
        salePriceField = new JTextField();
        saveButton = new JButton("Save");
        uploadImageButton = new JButton("Upload Image");
        imageLabel = new JLabel();

        // Set up layout using GroupLayout or another layout manager of your choice
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(new JLabel("Name"))
                .addComponent(new JLabel("Description"))
                .addComponent(new JLabel("Purchase Price"))
                .addComponent(new JLabel("Sale Price"))
                .addComponent(new JLabel("Image"))
                .addComponent(saveButton)
                .addComponent(uploadImageButton)
        );
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(nameField)
                .addComponent(descriptionArea)
                .addComponent(purchasePriceField)
                .addComponent(salePriceField)
                .addComponent(imageLabel)
        );
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(new JLabel("Name"))
                .addComponent(nameField)
        );
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(new JLabel("Description"))
                .addComponent(descriptionArea)
        );
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(new JLabel("Purchase Price"))
                .addComponent(purchasePriceField)
        );
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(new JLabel("Sale Price"))
                .addComponent(salePriceField)
        );
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(new JLabel("Image"))
                .addComponent(imageLabel)
        );
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(saveButton)
                .addComponent(uploadImageButton)
        );
        layout.setVerticalGroup(vGroup);

        // Attach action listeners
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

        // Add components to the layout
        add(panel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        // Set other dialog properties like size, default close operation, etc.
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
        // For simplicity, assuming product.getImage() returns a byte array
        displayImage(product.getImage());
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
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                byte[] imageData = Files.readAllBytes(selectedFile.toPath());
                currentProduct.setImage(imageData);
                displayImage(imageData);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error reading image file.");
            }
        }
    }

    private void displayImage(byte[] imageData) {
            if (imageData != null && imageData.length > 0) {
                ImageIcon imageIcon = new ImageIcon(imageData);
                Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(scaledImage);
                imageLabel.setIcon(imageIcon);
            } else {
                imageLabel.setIcon(null); // Clear the icon if there is no image
            }

    }
}

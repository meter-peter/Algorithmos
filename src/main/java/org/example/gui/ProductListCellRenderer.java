package org.example.gui;

import org.example.models.Product;

import javax.swing.*;
import java.awt.*;

public class ProductListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Product) {
            Product product = (Product) value;
            label.setIcon(new ImageIcon(product.getImage())); // Set the product image as the icon
            label.setText(product.getName() + " - $" + product.getSalePrice()); // Display product name and sell price
        }
        return label;
    }
}

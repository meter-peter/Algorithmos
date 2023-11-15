package org.example.gui;

import org.example.controllers.UserController;
import org.example.models.User;
import org.example.tables.UserTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class UserPanel extends JPanel {

    private JTable userTable;
    private UserTableModel userTableModel;
    private JButton createUserButton;
    private JButton editUserButton;
    private JButton removeUserButton;
    private JLabel userCountLabel;

    private UserController userController;

    public UserPanel(UserController userController) {
        this.userController = userController;
        initializeUI();
        setTableData(userController.getAllUsers());
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        userTableModel = new UserTableModel();
        userTable = new JTable(userTableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        createUserButton = new JButton("Create User");
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUser();
            }
        });

        editUserButton = new JButton("Edit User");
        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUser();
            }
        });

        removeUserButton = new JButton("Remove User");
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeUser();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createUserButton);
        buttonPanel.add(editUserButton);
        buttonPanel.add(removeUserButton);

        add(buttonPanel, BorderLayout.PAGE_END);

        userCountLabel = new JLabel("Users Count: 0");
        add(userCountLabel, BorderLayout.SOUTH);

        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editUser();
                }
            }
        });
    }


    public void setTableData(List<User> users) {
        userTableModel.setUsers(users);
        userTableModel.fireTableDataChanged();
        updateUsersCountLabel();
    }

    private void createUser() {
        String username = JOptionPane.showInputDialog(this, "Enter username:");
        if (username != null && !username.isEmpty()) {
            userController.createUser(new User(username));
            setTableData(userController.getAllUsers());
        } else {
            JOptionPane.showMessageDialog(this, "Username cannot be empty.");
        }
    }

    private void editUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            User selectedUser = userTableModel.getUserAt(selectedRow);
            String newUsername = JOptionPane.showInputDialog(this, "Enter new username:", selectedUser.getUsername());
            if (newUsername != null && !newUsername.isEmpty()) {
                selectedUser.setUsername(newUsername);
                userController.updateUser(selectedUser);
                setTableData(userController.getAllUsers());
            } else {
                JOptionPane.showMessageDialog(this, "Username cannot be empty.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to edit.");
        }
    }

    private void removeUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            User selectedUser = userTableModel.getUserAt(selectedRow);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this user?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                userController.removeUser(selectedUser);
                setTableData(userController.getAllUsers());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to remove.");
        }
    }

    private void updateUsersCountLabel() {
        int userCount = userTableModel.getRowCount();
        userCountLabel.setText("Users Count: " + userCount);
    }
}

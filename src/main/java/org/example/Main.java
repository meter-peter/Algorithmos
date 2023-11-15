package org.example;

import org.example.gui.MainFrame;
import org.example.gui.UserPanel;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.swing.*;

public class Main {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        // Initialize Hibernate
        configureHibernate();

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void configureHibernate() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml"); // assuming you have hibernate.cfg.xml in the classpath

            // If you don't have a hibernate.cfg.xml file, you can set properties programmatically, for example:
            // configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/yourdatabase");

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing Hibernate: " + e.getMessage(), e);
        }
    }

    private static void createAndShowGUI() {
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                };


}

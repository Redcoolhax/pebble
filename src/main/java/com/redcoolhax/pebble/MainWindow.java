package com.redcoolhax.pebble;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class MainWindow extends JFrame {
    private JComboBox<String> requestMethodSelection;

    public MainWindow() {
        super("Pebble");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        String[] requestMethods = {
            "GET",
            "POST",
            "PUT",
            "DELETE",
            "PATCH",
            "HEAD",
            "OPTIONS",
            "CONNECT",
            "TRACE"
        };
        requestMethodSelection = new JComboBox<>(requestMethods);
        requestMethodSelection.setBounds(50, 50, 150, 30);
        add(requestMethodSelection);

        setVisible(true);
    }
}
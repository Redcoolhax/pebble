package com.redcoolhax.pebble;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainWindow extends JFrame {
    private JComboBox<String> requestMethodSelection;
    private JTextField uriField;
    private JTextArea requestBody;

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
        requestMethodSelection.setBounds(10, 10, 150, 30);
        add(requestMethodSelection);

        uriField = new JTextField();
        uriField.setBounds(10, 50, 150, 30);
        add(uriField);

        requestBody = new JTextArea();
        requestBody.setBounds(50, 100, 300, 200);
        add(requestBody);

        setVisible(true);
    }
}